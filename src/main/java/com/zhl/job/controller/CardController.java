package com.zhl.job.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.UserType;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.impl.CardService;
import com.zhl.job.service.impl.CommonService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;
/**
 * 
  * @ClassName: CardController
  * @author zhaotisheng	
  * @date 2017年3月16日 下午2:43:35
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@RequestMapping(value = "/card")
public class CardController extends BaseController{

	private Logger logs = LoggerFactory.getLogger(CardController.class);
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value = "/list")
	public void list(Model model, HttpServletRequest request) throws NoLoginException {
		checkLoginStatus(request);
		logs.debug("##>>>list Request OK!");
		String loginUserId = super.getLoginUserId(request);
		Card card = new Card();
		card.setUserId(super.getLoginUserId(request));
		card.setIsdel(IsDel.CODE00.getCode());
		List<Card> cards=cardService.getCardListByUserId(card);
		model.addAttribute("cards",cards);
		String loginUserType = super.getLoginUserType(request);
		//企业和个人的菜单区分
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			model.addAttribute(Constant.DIV_ACTIVE, DivActive.MY_ACC.getCode());
			commonService.setUserInfo(request, model, loginUserId);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			model.addAttribute(Constant.DIV_ACTIVE, DivActive.ACC_INF.getCode());
			commonService.setCompanyInfo(request,model, loginUserId);
		}
	}
	/**
	 * @throws NoLoginException 
	 * 
	  * 解绑
	  *
	  * @Title: bankName
	  * @param @param model
	  * @param @param request
	  * @param @param card
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	@RequestMapping(value="/unBind", method={RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public @ResponseBody Object unBind(Model model, HttpServletRequest request,Card card) throws NoLoginException {
		logs.debug("##>>>unBind get Request OK!");
		checkLoginStatus(request);
		if(Stringer.isNullOrEmpty(card.getId())){
			return error("卡id不能为空");
		}
		
		ResponseEntity res=new ResponseEntity();
		//模拟
		return cardService.unBindCard(card,res);
	}
	
}
