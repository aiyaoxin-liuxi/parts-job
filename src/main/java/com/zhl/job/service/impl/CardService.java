package com.zhl.job.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.pub.util.memcached.MemcacheManager;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhl.job.controller.CompanyInfoController;
import com.zhl.job.dao.ICardDao;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.CardBin;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.service.ICardBinService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;

/**
 * 
  * @ClassName: CardService
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月16日 上午11:51:42
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Service
@Transactional
public class CardService {

	private Logger logs = LoggerFactory.getLogger(CompanyInfoController.class);
	
	@Autowired
	ICardDao cardDao;
	
	@Resource(name = "cardBinService")
	private ICardBinService cardBinService;
	
	public Card getCardByUserIdAndCardNo(Card card) {
		return cardDao.getCardByUserIdAndCardNo(card);
	}

	public Object getbankNameFromInterface(Card card, ResponseEntity res) {
//		if(Stringer.isNullOrEmpty(url)  || Stringer.isNullOrEmpty(userId)  || Stringer.isNullOrEmpty(pwd) ){
//			throw new RuntimeException("配置参数异常....");
//		}
//		Map<String,String> map=new HashMap<String,String>();
//		map.put("userId", userId);
//		map.put("pwd", MD5.MD5Encode(pwd));
//		map.put("funCode", "0004");
//		map.put("version", "1.0.0");
//		map.put("cardBin", card);
//		String post = HttpClientHelper.doHttpClient(url, "POST", "utf-8", JsonUtil.toJson(map).toString(), "60000","application/json","");
//		if(Stringer.isNullOrEmpty(post)){
//			log.debug("请求kabin返回的结果是空：\t\n\r 空");
//			return null;
//		}
//		log.debug("请求kabin返回的结果：\t\n\r"+post);
		BankTemp bankTemp = new BankTemp();
		bankTemp.bankId="ICBC";
		bankTemp.bankName="工商银行";
		
		res.setSuccess(true);
		res.setData(bankTemp);
		return res.toJson();
	}

	class BankTemp{
		public String bankId;
		public String bankName;
	}

	/**
	 * 
	  * insetOne绑定
	  * @throws
	 */
	public Object insetOne(Card card, ResponseEntity res) {
		Date now = new Date();
		card.setId(KeySn.getKey());
		card.setBinddate(now);
		card.setCreatedate(now);
		int i = cardDao.insertOne(card);
		return Stringer.commonOperation(i, "绑卡", res);
	}

	public List<Card> getCardListByUserId(Card card) {
		return cardDao.selectCardsByUserIdAndIsdel(card);
	}

	/**
	 * 
	  * unBindCard解绑
	  *
	  * @Title: unBindCard
	  * @Description: TODO
	  * @param @param card
	  * @param @param res
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	public Object unBindCard(Card card, ResponseEntity res) {
		card.setIsdel(IsDel.CODE01.getCode());
		int i = cardDao.updateIsdelOneById(card);
		return Stringer.commonOperation(i, "解绑", res);
	}

	public Card getCardById(String bank) {
		
		Card card=new Card();
		card.setId(bank);
		card.setIsdel(IsDel.CODE00.getCode());
		
		return cardDao.getCardByIdAndIsdel(card);
	}

	@SuppressWarnings("unchecked")
	public Object getbankNameFromCache(Card card, ResponseEntity res) {
		Object object = MemcacheManager.getInstance().get(Constant.CARD_BIN);
		if(Stringer.isNullOrEmpty(object)){
			init();
		}
		object = MemcacheManager.getInstance().get(Constant.CARD_BIN);
		
		if(Stringer.isNullOrEmpty(object)){
			res.setSuccess(false);
			res.setData("缓存异常");res.setErrmsg("缓存异常");
			return res.toJson();
		}
		Map<String, Object> map=(Map<String, Object>) object;
		CardBin cardBin=getBankInfo(card,map);
		if(Stringer.isNullOrEmpty(cardBin)){
			res.setSuccess(false);
			res.setData("找不到对应银行");
			res.setErrmsg("找不到对应银行");
			return res.toJson();
		}
		BankTemp bankTemp = new BankTemp();
		bankTemp.bankId="xxx";
		bankTemp.bankName=cardBin.getIssincName();
		res.setSuccess(true);
		res.setData(bankTemp);
		return res.toJson();
	}

	private void init() {
		logs.info("开始加载卡bin信息...start");
        List<CardBin> cardBinList = cardBinService.queryCardBinAll();
        if(null != cardBinList && cardBinList.size() > 0){
        	Map<String, Object> carBinMap = new HashMap<String, Object>();
        	for (CardBin cardBin : cardBinList) {
        		carBinMap.put(cardBin.getCardBin(), cardBin);
			}
        	
        	MemcacheManager.getInstance().add(Constant.CARD_BIN, carBinMap);
        }
        logs.info("卡bin信息加载完成...end");
	}

	private CardBin getBankInfo(Card card, Map<String, Object> map) {
//		map.get(key)
		String cardNo = card.getCardNo();
		if(Stringer.isNullOrEmpty(cardNo)){
			return null;
		}
		//6 7 8 9 10
		List<String> list=new ArrayList<String>();
		String str6 = cardNo.substring(0, 6); list.add(str6);
		String str7 = cardNo.substring(0, 7); list.add(str7);
		String str8 = cardNo.substring(0, 8); list.add(str8);
		String str9 = cardNo.substring(0, 9); list.add(str9);
		String str10 = cardNo.substring(0, 10); list.add(str10);
		CardBin cardBin=null;
		for(String key:list){
			Object object = map.get(key);
			if(!Stringer.isNullOrEmpty(object)){
				cardBin=(CardBin) object;
				break;
			}
		}
		return cardBin;
	}

//	private BankInfo getBankInfo(List<BankInfo> list) {
//		if(Stringer.isNullOrEmpty(list)){
//			return null;
//		}
//		for( BankInfo bankInfo :list){
//			
//		}
//		return null;
//	}
}
