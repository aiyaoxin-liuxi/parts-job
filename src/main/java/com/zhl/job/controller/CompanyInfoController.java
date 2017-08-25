package com.zhl.job.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zhl.job.controller.common.BaseController;
import com.zhl.job.interceptor.anno.NoAuth;
import com.zhl.job.pay.IPay;
import com.zhl.job.pay.common.PayFactory;
import com.zhl.job.pay.common.PayFactoryEnum;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.CardValidRes;
import com.zhl.job.pojo.CompanyInfo;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.UserType;
import com.zhl.job.pojo.enums.company.CompanyPeopleNum;
import com.zhl.job.pojo.enums.company.CompanyType;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.CardValidResService;
import com.zhl.job.service.impl.CardService;
import com.zhl.job.service.impl.CommonService;
import com.zhl.job.service.impl.CompanyInfoService;
import com.zhl.job.service.impl.SendSMSService;
import com.zhl.job.service.impl.UserLogInfoService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.RandomGenerator;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.VerifyCoder;

/**
 * 企业用户
  * @ClassName: CompanyInfoController
  * @author zhaotisheng	
  * @date 2017年3月13日 下午4:42:23
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@RequestMapping(value = "/compayinfo")
public class CompanyInfoController extends BaseController{
	
	private static final String SMSCODE_KEY=Constant.SMSCODE_KEY;

	private Logger logs = LoggerFactory.getLogger(CompanyInfoController.class);
	
	@Autowired
	private SendSMSService sendSMSService;
	
	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CardValidResService cardValidResService;
	
	
	@Autowired
	@Qualifier("userLogInfoService")
	private UserLogInfoService userLogInfoService;
	/**
	 * 
	  * 注册
	  *
	  * @Title: main0
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/register")@NoAuth
	public void register(Model model, HttpServletRequest request,String userType) {
		logs.debug("##>>>register Request OK!");
		model.addAttribute("userType", userType);
	}
	//registerselect
	@RequestMapping(value = "/registerselect")@NoAuth
	public void registerselect(Model model, HttpServletRequest request) {
		logs.debug("##>>>register Request OK!");
	}
	
	@RequestMapping(value = "/register",method={RequestMethod.POST})@NoAuth
	public synchronized @ResponseBody Object  registerPost(Model model, HttpServletRequest request,UserLogInfo userLogInfo,String verifycode) {
		logs.debug("##>>>registerPost Request OK!");
		if(Stringer.isNullOrEmpty(verifycode)){
			return error("请填写验证码");
		}
		Object session_verifyCode = request.getSession().getAttribute(SMSCODE_KEY);
		if(Stringer.isNullOrEmpty(session_verifyCode)){
			return error("验证码失效，请重新获取验证码");
		}
		if(!session_verifyCode.equals(verifycode)){
			return error("验证码错误");
		}
		try {
			request.getSession().removeAttribute(SMSCODE_KEY);
		} catch (Exception e) {
		}
		//注册
		return companyInfoService.register(userLogInfo,request);
	}
	/**
	 * 
	  * 生成验证码
	  *
	  * @Title: verifycode
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/sendSMS",method={RequestMethod.POST})@NoAuth
	public @ResponseBody Object sendSMS(Model model, HttpServletRequest request,String mobile) {
		logs.debug("##>>>verifycode Request OK!");
		if(!Stringer.isMobile(mobile)){
			logs.debug("##>>>verifycode Request OK! 请填写正确的手机号");
			return error("请填写正确的手机号");
		}
		//判断手机号是否注册过  
		//数据库验重
		UserLogInfo userLogInfo = new UserLogInfo();userLogInfo.setMobile(mobile);
		boolean selectByUserLogInfoState = userLogInfoService.selectByUserLogInfoState(userLogInfo);
		if( selectByUserLogInfoState){
			return error("手机号已经注册");
		}		
		String randomDigital = RandomGenerator.randomDigital(6);
		request.getSession().setAttribute(SMSCODE_KEY, randomDigital);
		logs.info("##>>>准备发送短信验证码:"+randomDigital);
		return sendSMSService.sendVerifyCode(mobile,randomDigital);
	}
	
	@RequestMapping(value = "/login")@NoAuth
	public void loginGet(Model model, HttpServletRequest request) {
		logs.debug("##>>>login get Request OK!");
	}
	
	@RequestMapping(value = "/login",method={RequestMethod.POST})@NoAuth
	public @ResponseBody Object loginPost(Model model, HttpServletRequest request,UserLogInfo userLogInfo,String verifyCode) {
		logs.debug("##>>>loginPost get Request OK!");
		if(Stringer.isNullOrEmpty(verifyCode)){
			return error("验证码不能为空");
		}
		Object verifyCodeSession = request.getSession().getAttribute(Constant.VERIFY_CODE);
		if(Stringer.isNullOrEmpty(verifyCodeSession)){
			return error("验证码过期，请重新获取");
		}
		if(!verifyCode.equals(verifyCodeSession)){
			return error("验证码不正确");
		}
		try {
			request.getSession().removeAttribute(Constant.VERIFY_CODE);
		} catch (Exception e) {
		}
		
		return companyInfoService.login(userLogInfo,request);
	}
	//登出
	@RequestMapping(value = "/loginOut")@NoAuth
	public String loginOut(Model model, HttpServletRequest request) {
		logs.debug("##>>>loginOut Request OK!");
		try {
			String userType = request.getParameter("userType");
			if(!Stringer.isNullOrEmpty(userType)){
				model.addAttribute("userType", userType);
			}
			request.getSession().invalidate();
		} catch (Exception e) {
		}
		model.addAttribute("loginOut", "loginOut");
		return "/compayinfo/login";
	}
	/**
	 * 
	  * getVerifyCodeImage验证码
	  * @Title: getVerifyCodeImage
	  * @param @param request
	  * @param @param response
	  * @param @throws IOException    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value="/verifyCodeImage")@NoAuth
	public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logs.debug("##>>>verifyCodeImage get Request OK!");
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCoder.generateTextCode(VerifyCoder.TYPE_NUM_ONLY, 4, null);
		// 将验证码放到HttpSession里面
		request.getSession().setAttribute(Constant.VERIFY_CODE, verifyCode);
		logs.debug("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCoder.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE,
				Color.BLACK, null);
		// 写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}
	
	/**
	 * @throws NoLoginException 
	 * @throws NotLoginException 
	 * 
	  * fill信息
	  *
	  * @Title: fillGet
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/fill")
	public String fillGet(Model model, HttpServletRequest request,CompanyInfo companyInfo,UserLogInfo userLogInfo,String modify) throws NoLoginException {
		logs.debug("###>>>准备fill：");
		checkLoginStatus(request);
		CompanyInfo companyInfonew = new CompanyInfo();
		if(!Stringer.isNullOrEmpty(companyInfo.getCid())){
			logs.debug("###>>>编辑 ...cid："+companyInfo.getCid());
			companyInfonew= companyInfoService.getCompanyInfoByCid(companyInfo);
			
		}
		if(Stringer.isNullOrEmpty(companyInfonew.getCid())){
			logs.debug("###>>>准备补全：");
			String userId=getLoginUserId(request);
			userLogInfo.setId(userId);//.setUserId(userId);
			CompanyInfo companyInfoByUserId = companyInfoService.getCompanyInfoByUserId(userLogInfo);
			if(!Stringer.isNullOrEmpty(companyInfoByUserId)){
				companyInfonew=companyInfoByUserId;
			}
		}
		//登陆成功跳转信息补全页面
//		if(Stringer.isNullOrEmpty(companyInfo.getCid()) &&  !Stringer.isNullOrEmpty(userLogInfo.getId())){
//			logs.debug("###>>>登陆成功跳转信息补全页面 ...cid："+companyInfo.getCid());
//			CompanyInfo companyInfoByUserId = companyInfoService.getCompanyInfoByUserId(userLogInfo);
////			if(!Stringer.isNullOrEmpty(companyInfoByUserId)){
////				//已经补全的，跳转到发布兼职页面 20170326 修改
////				logs.debug("###>>>已经补全的，跳转到发布兼职页面 20170326 修改 ：companyInfo.getCid()"+companyInfo.getCid());
////				return "redirect:/work/toReleaseJobInfo";
////			}
//			if( !Stringer.isNullOrEmpty(companyInfoByUserId)){
//				companyInfonew=companyInfoByUserId;
//			}
//		}
		if(!Stringer.isNullOrEmpty(modify)){
			model.addAttribute("modify", modify);
		}
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.COM_INF.getCode());
		model.addAttribute("companyInfo", companyInfonew);
		model.addAttribute("companyTypes", CompanyType.values());//
		model.addAttribute("companyPeopleNums", CompanyPeopleNum.values());
		logs.debug("##>>>fill get Request OK!");
		String loginUserId = super.getLoginUserId(request);
		UserLogInfo selectByPk = userLogInfoService.selectByPk(loginUserId);
 	    commonService.setLoginInfo(request,selectByPk);
		return "/compayinfo/fill";
	}
	/**
	 * @throws NoLoginException 
	 * 
	  * fillPost save data
	  * @Title: fillPost
	  * @param @param model
	  * @param @param request
	  * @param @param companyInfo
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/fill",method={RequestMethod.POST})
	public @ResponseBody Object  fillPost(Model model, HttpServletRequest request,CompanyInfo companyInfo) throws NoLoginException {
		checkLoginStatus(request);
		if(Stringer.isNullOrEmpty(companyInfo.getCompanyName())){
			return error("公司名称不能为空");
		}
		if(Stringer.haveSpecialCharacter(companyInfo.getCompanyName())){
			return error("公司名称不能有特殊字符");
		}
		if(!Stringer.isNullOrEmpty(companyInfo.getTelephone())){
			if(!Stringer.isFixedTelephone(companyInfo.getTelephone())){
				return error("请正确填写公司固定电话");
			}
		}
		if(!Stringer.isNullOrEmpty(companyInfo.getAddressDetail())){
			if(Stringer.haveSpecialCharacter(companyInfo.getAddressDetail())){
				return error("公司地址不能有特殊字符");
			}
			if(companyInfo.getAddressDetail().length()>50){
				return error("公司地址太长");
			}
		}
		String userId=getLoginUserId(request);
		companyInfo.setUserId(userId);
		companyInfo.setMobile(super.getLoginUserMobile(request));
		String logoImg = companyInfo.getLogoImg();
		if(!Stringer.isNullOrEmpty(logoImg)){
			logoImg=logoImg.replaceAll("\\\\", "/").replace(",","");
		}
		companyInfo.setLogoImg(logoImg);
		if(Stringer.isNullOrEmpty(companyInfo.getCid())){
			return companyInfoService.insertOne(companyInfo);
		}else{
			String loginUserId = super.getLoginUserId(request);
			companyInfo.setUpdateUserId(loginUserId);
			commonService.setCompanyInfoId(request,userId);//更新session
			return companyInfoService.updateOne(request,companyInfo);
		}
	}
	/**
	 * @throws NoLoginException 
	 * 
	  * 信息查看
	  *
	  * @Title: fillview
	  * @param @param model
	  * @param @param request
	  * @param @param companyInfo    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/fillview")
	public void  fillview(Model model, HttpServletRequest request,CompanyInfo companyInfo) throws NoLoginException {
		checkLoginStatus(request);
		if(!Stringer.isNullOrEmpty(companyInfo.getCid())){
			CompanyInfo companyInfod=companyInfoService.getCompanyInfoByCid(companyInfo);
			
			String companyType = companyInfod.getCompanyType();
			companyInfod.setCompanyType(CompanyType.parseOf(companyType).getName());
			
			String companyPeopleNum = companyInfod.getCompanyPeopleNum();
			companyInfod.setCompanyPeopleNum(CompanyPeopleNum.parseOf(companyPeopleNum).getName());
			model.addAttribute("companyInfo", companyInfod);
		}
	}
	/**
	 * @throws NoLoginException 
	 * 
	  * bindcard
	  *
	  * @Title: bindcard
	  * @param @param model
	  * @param @param request
	  * @param @param companyInfo    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/bindcard")
	public void  bindcardGet(Model model, HttpServletRequest request,CompanyInfo companyInfo) throws NoLoginException {
		logs.debug("##>>>bindcardGet get Request OK!");
		checkLoginStatus(request);
		model.addAttribute("card", new Card());
		
		String loginUserId = super.getLoginUserId(request);
		String loginUserType = super.getLoginUserType(request);
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			model.addAttribute(Constant.DIV_ACTIVE, DivActive.MY_ACC.getCode());
			commonService.setUserInfo(request, model, loginUserId);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			model.addAttribute(Constant.DIV_ACTIVE, DivActive.ACC_INF.getCode());
			commonService.setCompanyInfo(request,model, loginUserId);
		}
	}
	
	
	@RequestMapping(value = "/bindcard",method={RequestMethod.POST})
	public @ResponseBody Object  bindcardPost(Model model, HttpServletRequest request,Card card) throws NoLoginException {
		checkLoginStatus(request);
		logs.debug("##>>>bindcardPost get Request OK!");
		if(Stringer.isNullOrEmpty(card.getCardName()) || Stringer.isNullOrEmpty(card.getBindIdcard())
		|| Stringer.isNullOrEmpty(card.getCardNo())|| Stringer.isNullOrEmpty(card.getMobile())
		|| Stringer.isNullOrEmpty(card.getBank()) || Stringer.isNullOrEmpty(card.getBankname())
		){
			return error("请填写必填项");
		}
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
		//绑卡校验
		//开发者模式不验证
		if(Stringer.isNullOrEmpty(Constant.DEV_SWITCH)  || !Constant.DEV_SWITCH.equals("1")){//测试的时候可以用这个，不真正发短信，验证码看log
			logs.debug("##>>>bindcardPost 开发者模式不验证");
			return cardService.insetOne(card,res);
		}
		//非开发者模式验证卡的有效性
		logs.debug("##>>>bindcardPost 非开发者模式验证卡的有效性");
		
		invokeInterfaceValidCard(card,res);
		if(!res.isSuccess()){
			return res.toJson();
		}	
		return cardService.insetOne(card,res);
	}
	
	//银行卡有效性验证
	private void invokeInterfaceValidCard(Card card, ResponseEntity res) {
		Object[] objArr = new Object[]{card,res};
		// ***************调用银行加密方法组装银行参数***********************
		IPay payInstance = new PayFactory().getInstance(PayFactoryEnum.CARDVALID_KAOLA.getCode());
		res = payInstance.validCard(objArr);
		boolean success = res.isSuccess();
		String errmsg = res.getErrmsg();
		//save db-------------------
		saveValidRes2Db(card,res);
		//以接口返回的为准
		res.setSuccess(success);
		res.setErrmsg(errmsg);
	}
	
	private void saveValidRes2Db(Card card, ResponseEntity res) {
		CardValidRes cardValidRes = new CardValidRes();
		cardValidRes.setCid(KeySn.getKey());
		cardValidRes.setCreatedate(new Date());
		cardValidRes.setUserId(card.getUserId());
		cardValidRes.setIsdel(IsDel.CODE00.getCode());
		if(res.isSuccess()){
			cardValidRes.setRes(1);
		}else{
			cardValidRes.setRes(0);
		}
		
		@SuppressWarnings("unchecked")
		Map<String, String> dataMap = (Map<String, String>) res.getData();
		cardValidRes.setUploadStr(dataMap.get("uploadStr"));
		cardValidRes.setReturnStr(dataMap.get("returnStr"));
		int i = cardValidResService.inertOne(cardValidRes);
		Stringer.commonOperation(i, "插入银行卡有效性验证表", res);
	}
	@RequestMapping(value="/bankName", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object bankName(Model model, HttpServletRequest request,Card card) {
		logs.debug("##>>>bankName get Request OK!");
		
		if(Stringer.isNullOrEmpty(card.getCardNo())){
			return error("卡号不能为空");
		}
		
		ResponseEntity res=new ResponseEntity();
		//模拟
//		return cardService.getbankNameFromInterface(card,res);
		return cardService.getbankNameFromCache(card,res);
	}
	/**
	 * @throws UnsupportedEncodingException 
	 * 
	  * upload文件上传
	  * @Title: upload
	  * @param @param model
	  * @param @param request
	  * @param @param companyInfo
	  * @param @return
	  * @param @throws NoLoginException    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/upload")
	public @ResponseBody String  upload(Model model, 
			HttpServletRequest orequest,MultipartHttpServletRequest request, HttpServletResponse response
			,String titleFlag,CompanyInfo  companyInfo) throws NoLoginException, UnsupportedEncodingException {
		logs.debug("##>>>upload get Request OK!");
		ResponseEntity res=new ResponseEntity();
		if(!Stringer.isNullOrEmpty(titleFlag) && titleFlag.equals("titleFlag")){
			//只修改头像
			return onlyModifyImg(request,res,companyInfo);
		}
		res=companyInfoService.upload(request,res);
		if(!res.isSuccess()){
			res.setSuccess(false);
			res.setErrmsg("upload fail");
			return res.toJson().toString();
		}
		res.setSuccess(true);
		//Constant.IMAGE_RESET_PATH+
		res.setData(res.getData());
		return res.toJson().toString();
	}
	private String onlyModifyImg(MultipartHttpServletRequest request, ResponseEntity res, CompanyInfo companyInfo) {
		if(Stringer.isNullOrEmpty(companyInfo)  ||Stringer.isNullOrEmpty(companyInfo.getCid()) ){
			return error("参数异常").toString();
		}
		res=companyInfoService.upload(request,res);
		if(!res.isSuccess()){
			return error("upload fail").toString();
		}
		//updateConstant.IMAGE_RESET_PATH+
		CompanyInfo companyInfoDb= companyInfoService.getCompanyInfoByCid(companyInfo);
		companyInfoDb.setLogoImg(res.getData().toString());
		Object updateOne = companyInfoService.updateOne(request, companyInfoDb);
		Object bean = JsonUtil.toBean((StringBuilder)updateOne, ResponseEntity.class);
		if( ((ResponseEntity)bean).isSuccess()){
			//Constant.IMAGE_RESET_PATH+
			res.setData(res.getData());
			return res.toJson().toString();
		}
		return updateOne.toString();
	}
	//安全中心
	@RequestMapping(value = "/safe")
	public void  safe(Model model, HttpServletRequest request,CompanyInfo companyInfo) throws NoLoginException {
		logs.debug("##>>>bindcardGet get Request OK!");
		checkLoginStatus(request);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.SEC_CENTER.getCode());
		
		String loginUserId = super.getLoginUserId(request);
		String loginUserType = super.getLoginUserType(request);
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			commonService.setUserInfo(request, model, loginUserId);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			commonService.setCompanyInfo(request,model, loginUserId);
		}
	}
	//认证
	@RequestMapping(value = "/verify")
	public void  verifyGet(Model model, HttpServletRequest request,CompanyInfo companyInfo) throws NoLoginException {
		logs.debug("##>>>verify get Request OK!");
		checkLoginStatus(request);
		String userId=getLoginUserId(request);
		UserLogInfo userLogInfo=new UserLogInfo();
		userLogInfo.setId(userId);//.setUserId(userId);
		CompanyInfo companyInfoByUserId = companyInfoService.getCompanyInfoByUserId(userLogInfo);
		model.addAttribute("companyInfo", companyInfoByUserId);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.COM_INF.getCode());
	}
	//更新verify 信息
	@RequestMapping(value = "/verify",method={RequestMethod.POST})
	public @ResponseBody Object  verifyPost(Model model, HttpServletRequest request,CompanyInfo companyInfo) throws NoLoginException {
		logs.debug("##>>>verify verifyPost Request OK!");
		String companyidNo = companyInfo.getCompanyidNo();
		if(Stringer.isNullOrEmpty(companyidNo) || companyidNo.length()>30){
			return error("请规范填写营业执照号");
		}
		String logoImg = companyInfo.getCompanyidImg();
		if(Stringer.isNullOrEmpty(logoImg)){
			return error("请上传营业执照");
		}
		if(!Stringer.isNullOrEmpty(logoImg)){
			logoImg=logoImg.replaceAll("\\\\", "/").replace(",","");
			companyInfo.setCompanyidImg(logoImg);
		}
		
		companyInfo.setComLevel("2");//提交审核两个星
		setState(companyInfo);
		String loginUserId = super.getLoginUserId(request);
		companyInfo.setUpdateUserId(loginUserId);
		return companyInfoService.updateOne(request,companyInfo);
	}
	private void setState(CompanyInfo companyInfo) {
		CompanyInfo companyInfoByCid = companyInfoService.getCompanyInfoByCid(companyInfo);
		if( !Stringer.isNullOrEmpty(companyInfoByCid.getState())&&companyInfoByCid.getState().equals(State.AUDIT_SUCC.getCode())){
			companyInfo.setState(State.AUDIT_SUCC.getCode());
		}else{
			companyInfo.setState(State.WAIT_AUDIT.getCode());
		}
	}
	//审核
	@RequestMapping(value = "/audit")
	public void  audit(Model model, HttpServletRequest request,CompanyInfo companyInfo) throws NoLoginException {
		logs.debug("##>>>verify get Request OK!");
		checkLoginStatus(request);
		String userId=getLoginUserId(request);
		UserLogInfo userLogInfo=new UserLogInfo();
		userLogInfo.setId(userId);//.setUserId(userId);
		CompanyInfo companyInfoByUserId = companyInfoService.getCompanyInfoByUserId(userLogInfo);
		model.addAttribute("companyInfo", companyInfoByUserId);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.COM_INF.getCode());
	}
	@RequestMapping(value = "/auditSucc")
	public void  auditSucc(Model model, HttpServletRequest request,CompanyInfo companyInfo) throws NoLoginException {
		logs.debug("##>>>auditSucc get Request OK!");
		checkLoginStatus(request);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.COM_INF.getCode());
	}
	
}
