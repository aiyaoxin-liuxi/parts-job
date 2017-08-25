package com.zhl.job.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.UserType;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.service.impl.CardService;
import com.zhl.job.service.impl.CashService;
import com.zhl.job.service.impl.CommonService;
import com.zhl.job.service.impl.SendSMSService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.RandomGenerator;
import com.zhl.job.util.Stringer;
/**
 * 用户账户信息
 * @author wxw
 *
 */
@Controller
@RequestMapping(value = "/userAccount")
public class UserAccountController extends BaseController{
	private Logger logs = LoggerFactory.getLogger(UserAccountController.class);
	private static final String SMSCODE_KEY="smscode";
	@Resource
	private IUserLogInfoService userLogInfoService;
	@Autowired
	private CardService cardService;
	@Resource
	private	CashService cashService;
	@Autowired
	private SendSMSService sendSMSService;
	@Autowired
	private CommonService commonService;
	/**
	 * 个人账户
	 * @param model
	 * @param request
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/accountIndex")
	public String accountIndex(Model model,HttpServletRequest request) throws NoLoginException{
		checkLoginStatus(request);
		String userId = getLoginUserId(request);//
		UserLogInfo userLogInfo = userLogInfoService.selectByPk(userId);
		model.addAttribute("userLogInfo", userLogInfo);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.MY_ACC.getCode());
		
		String loginUserType = super.getLoginUserType(request);
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			commonService.setUserInfo(request, model);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			commonService.setCompanyInfo(request,model);
		}
		
		return "userAccount/accountIndex";
	}
	
	/**
	 * 提现
	 * @param model
	 * @param request
	 * @param id
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/accountCash")
	public void accountCash(Model model,HttpServletRequest request,String id) throws NoLoginException{
		checkLoginStatus(request);
		UserLogInfo userLogInfo = userLogInfoService.selectByPk(getLoginUserId(request));
		//查询用户绑定的银行卡
		Card card = new Card();
		card.setUserId(super.getLoginUserId(request));
		card.setIsdel(IsDel.CODE00.getCode());
		List<Card> cards=cardService.getCardListByUserId(card);
		model.addAttribute("userLogInfo", userLogInfo);
		model.addAttribute("cardList", cards);
		//add
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.MY_ACC.getCode());
		String loginUserType = super.getLoginUserType(request);
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			model.addAttribute(Constant.DIV_ACTIVE, DivActive.MY_ACC.getCode());
			commonService.setUserInfo(request, model);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			model.addAttribute(Constant.DIV_ACTIVE, DivActive.ACC_INF.getCode());
			commonService.setCompanyInfo(request,model);
		}
	}
	
	/**
	 * 用户提现
	 * @param model
	 * @param request
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/userCash")
	@ResponseBody
	public Object userCashe(Model model, HttpServletRequest request,double payamount,
			/*,String cardsChoose,payPassword*/UserLogInfo userLogInfo,String paypassword,Card card) throws NoLoginException {
//		checkLoginStatus(request);
		if(Stringer.isNullOrEmpty(payamount)){
			return error("请正确填写提现金额");
		}
		if(Stringer.isNullOrEmpty(card.getId())){
			return error("请选择银行卡");
		}
		String userId = super.getLoginUserId(request);
		UserLogInfo userLogInfoDb = userLogInfoService.selectByPk(userId);
		Card cardDb=cardService.getCardById(card.getId());
		if(Stringer.isNullOrEmpty(cardDb)){
			return error("银行卡已经不可用，请重新选择");
		}
		if(payamount<=0){
			return error("提现金额有误");
		}
		if(Stringer.isNullOrEmpty(userLogInfo.getPayPassword())){
			return error("请输入支付密码");
		}
		if(Stringer.isNullOrEmpty(userLogInfoDb.getPayPassword())){
			return error("请设置支付密码");
		}
		String encryptPayPwd = Stringer.encryptPayPwd(userLogInfo);
		if(!encryptPayPwd.equals(userLogInfoDb.getPayPassword())){
			return error("支付密码有误");
		}
				
		return cashService.cash(payamount, cardDb,userLogInfoDb);
	}
	
	/**
	 * 银行卡管理
	 * @param model
	 * @param request
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/cardsList")
	public void cardList(ModelMap model,HttpServletRequest request) throws NoLoginException{
		checkLoginStatus(request);
		//查询用户绑定的银行卡
		Card card = new Card();
		card.setUserId(super.getLoginUserId(request));
		card.setIsdel(IsDel.CODE00.getCode());
		List<Card> cards=cardService.getCardListByUserId(card);
		model.addAttribute("cardList", cards);
	}
	/**
	 * 添加银行卡
	 * @param model
	 * @param request
	 * @throws NoLoginException 
	 */
	@RequestMapping(value="/bindcard")
	public void bindcard(ModelMap model,HttpServletRequest request) throws NoLoginException{
		checkLoginStatus(request);
		String userId = getLoginUserId(request);//
		UserLogInfo userLogInfo = userLogInfoService.selectByPk(userId);
		model.addAttribute("userLogInfo", userLogInfo);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.MY_ACC.getCode());
	}
	/**
	 * 保存卡信息
	 * @param model
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value = "/savebindcard")
	public @ResponseBody Object  bindcardPost(Model model, HttpServletRequest request,Card card) {
		logs.debug("##>>>bindcardPost get Request OK!");
		
		if(Stringer.isNullOrEmpty(card.getCardName()) || Stringer.isNullOrEmpty(card.getBindIdcard())
		|| Stringer.isNullOrEmpty(card.getCardNo())|| Stringer.isNullOrEmpty(card.getMobile())
		|| Stringer.isNullOrEmpty(card.getBank()) || Stringer.isNullOrEmpty(card.getBankname())
		){
			return error("请填写必填项");
		}
		String cardNoTrim = StringUtils.trim(card.getCardNo());
		card.setCardNo(cardNoTrim);
		String loginUserId = super.getLoginUserId(request);
		card.setUserId(loginUserId);
		card.setIsdel(IsDel.CODE00.getCode());
		Card cardDb=cardService.getCardByUserIdAndCardNo(card);//unImpl
		if(!Stringer.isNullOrEmpty(cardDb)){
			return error("此卡已经绑定");
		}
		ResponseEntity res=new ResponseEntity();
		if(!Stringer.isMobile(card.getMobile())){
			return error("请填写正确手机号");
		}
		if(!Stringer.isDigit(card.getCardNo())){
			return error("请填写正确银行卡号");
		}
		if(!Stringer.isIDNo(card.getBindIdcard())){
			return error("请填写正确身份证号");
		}
		
		return cardService.insetOne(card,res);
	}
	/**
	 * 解绑银行卡
	 * @param model
	 * @param request
	 * @param card
	 * @return
	 */
	@RequestMapping(value="/unBind", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object unBind(Model model, HttpServletRequest request,Card card) {
		logs.debug("##>>>unBind get Request OK!");
		
		if(Stringer.isNullOrEmpty(card.getId())){
			return error("卡id不能为空");
		}
		
		ResponseEntity res=new ResponseEntity();
		//模拟
		return cardService.unBindCard(card,res);
	}
	
	
	
	@RequestMapping(value = "/safe")
	public void safe(Model model, HttpServletRequest request) throws NoLoginException {
		checkLoginStatus(request);
		UserLogInfo userLogInfo =new UserLogInfo();
		userLogInfo.setMobile(super.getLoginUserMobile(request));//
		UserLogInfo userLogInfoDb = userLogInfoService.getByMobile(userLogInfo);
		String modifyFlag=userLogInfoDb.getPayPassword() ==null?"0":"1";
		model.addAttribute("modifyFlag", modifyFlag);
	}
	

	/**
	 * 修改登录密码
	 * @param model
	 * @param request
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/modifypwd")
	public void updatepwdGet(Model model, HttpServletRequest request) throws NoLoginException {
		checkLoginStatus(request);
		logs.debug("##>>>updatepwd Request OK!");
		String m = super.getLoginUserMobile(request);
		model.addAttribute("mobile", m);
	}
	
	@RequestMapping(value = "/updatepwd")
	public @ResponseBody Object  updatepwdPost(Model model, HttpServletRequest request,String mobile,String logPassword,String newlogPassword,String renewlogPassword) {
		logs.debug("##>>>updatepwd Request OK!");
		if( ! newlogPassword.equals(renewlogPassword)){
			return error("两次输入不一致");
		}
		UserLogInfo userLogInfo = new UserLogInfo();
		userLogInfo.setMobile(mobile);
		UserLogInfo serLogInfoDb=userLogInfoService.getByMobile(userLogInfo);
		if(Stringer.isNullOrEmpty(serLogInfoDb)){
			return error("找不到对应的用户");
		}
		String logPasswordDb = serLogInfoDb.getLogPassword();
		if( !logPassword.equals(logPasswordDb)){
			return error("原密码不对");
		}
		serLogInfoDb.setLogPassword(newlogPassword);
		
		ResponseEntity res = new ResponseEntity();
		
		int i = userLogInfoService.updateByPrimaryKeySelective(serLogInfoDb);
		res.setData(serLogInfoDb.getId());
		return Stringer.commonOperation(i,"修改密码",res);
		
	}
	/**
	 * 设置/修改支付密码
	 * @param model
	 * @param request
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/modifypaypwd")
	public void modifypaypwd(Model model, HttpServletRequest request) throws NoLoginException {
		checkLoginStatus(request);
		UserLogInfo userLogInfo =new UserLogInfo();
		userLogInfo.setMobile(super.getLoginUserMobile(request));
		UserLogInfo userLogInfoDb = userLogInfoService.getByMobile(userLogInfo);
		String modifyFlag=userLogInfoDb.getPayPassword() ==null?"0":"1";
		model.addAttribute("modifyFlag", modifyFlag);
		model.addAttribute("id", userLogInfoDb.getId());
	}
	/**
	 * 保存支付密码
	 * @param userLogInfo
	 * @param model
	 * @param request
	 * @param modifyFlag
	 * @param rePayPassword
	 * @param newPayPassword
	 * @param reNewPayPassword
	 * @return
	 */
	@RequestMapping(value = "/updatepaypwd",method={RequestMethod.POST})
	public @ResponseBody Object  paypwdPost(String id,String modifyFlag,Model model, HttpServletRequest request,
			String payPassword,String rePayPassword,String newPayPassword,String reNewPayPassword) {
		logs.debug("##>>>paypwdPost Request OK! modifyFlag:"+modifyFlag);
		UserLogInfo userLogInfo = userLogInfoService.selectByPk(id);
		if(modifyFlag.equals("0")){//设置
			logs.debug("##>>>设置支付密码");
			if( Stringer.isNullOrEmpty(userLogInfo.getPayPassword()) || Stringer.isNullOrEmpty(rePayPassword) ){
				return error("支付密码和重复密码不可为空");
			}
			if( ! userLogInfo.getPayPassword().equals(rePayPassword)){
				return error("两次输入不一致");
			}
			ResponseEntity res = new ResponseEntity();
			int i = userLogInfoService.updateByPrimaryKeySelective(userLogInfo);
			return Stringer.commonOperation(i, "设置支付密码", res);
		}else if(modifyFlag.equals("1")){//修改
			logs.debug("##>>>修改支付密码");
			if( Stringer.isNullOrEmpty(userLogInfo.getPayPassword()) || Stringer.isNullOrEmpty(newPayPassword) ||Stringer.isNullOrEmpty(reNewPayPassword) ){
				return error("原密码，支付密码和重复密码不可为空");
			}
			if( ! newPayPassword.equals(reNewPayPassword)){
				return error("两次输入不一致");
			}
			if(!userLogInfo.getPayPassword().equals(payPassword)){
				return error("原密码有误");
			}
			userLogInfo.setPayPassword(reNewPayPassword);
			ResponseEntity res = new ResponseEntity();
			int i = userLogInfoService.updateByPrimaryKeySelective(userLogInfo);
			return Stringer.commonOperation(i, "修改支付密码", res);
		}else{
			logs.error("##>>>paypwdPost Request error,don't know what action ");
		}
		return null;
	}
	/**
	 * 找回支付密码
	 * @param model
	 * @param request
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/findpaypwd")
	public void findpaypwd(Model model, HttpServletRequest request) throws NoLoginException {
		checkLoginStatus(request);
		String mobile = super.getLoginUserMobile(request);
		model.addAttribute("mobile", mobile);
	}
	/**
	 * 发送短信
	 * @param model
	 * @param request
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/toSendSms")
	@ResponseBody
	public Object toSendSms(ModelMap model,HttpServletRequest request,String mobile){
		if(!Stringer.isMobile(mobile)){
			return error("请填写正确的手机号");
		}
		UserLogInfo info = new UserLogInfo();
		info.setMobile(mobile);
		String randomDigital = RandomGenerator.randomDigital(6);
		logs.info("=============="+randomDigital);
		request.getSession().setAttribute(SMSCODE_KEY, randomDigital);
		//TODO;调用短信发送方法
		return sendSMSService.sendVerifyCode(mobile,randomDigital);
	}
	//TODO..
	@RequestMapping(value = "/bindwx")
	public void bindwxGet(Model model, HttpServletRequest request) throws NoLoginException {
		logs.debug("##>>>paypwd bindwx OK!");
		checkLoginStatus(request);
		UserLogInfo userLogInfo =new UserLogInfo();
		userLogInfo.setMobile(super.getLoginUserMobile(request));//
		UserLogInfo userLogInfoDb = userLogInfoService.getByMobile(userLogInfo);
		String bindwx=userLogInfoDb.getWechatName() ==null?"0":"1";
		model.addAttribute("bindwx", bindwx);
		
		
	}
}
