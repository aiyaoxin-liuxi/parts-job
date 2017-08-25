package com.zhl.job.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.pub.util.memcached.MemcacheManager;

import com.zhl.job.pojo.BankInfo;
import com.zhl.job.pojo.CardBin;
import com.zhl.job.service.IBankInfoService;
import com.zhl.job.service.ICardBinService;
import com.zhl.job.service.IRegionService;
import com.zhl.job.util.Constant;

/**
 * 启动初始化类
 * @author 刘熙
 */
public class JobInit {
	
	@Resource(name = "regionService")
	private IRegionService regionService;
	@Resource(name = "bankInfoService")
	private IBankInfoService bankInfoService;
	@Resource(name = "cardBinService")
	private ICardBinService cardBinService;
	
	private Logger logger = Logger.getLogger(JobInit.class);
	
	public void initMethod() throws Exception {  
		logger.info("启动执行---start");
        
		logger.info("开始加载省市区信息...start");
        Map<String, Object> map = regionService.queryAll();
		Map<String, Object> cMap = (Map<String, Object>) map.get("cityMap");
		Map<String, Object> aMap = (Map<String, Object>) map.get("areaMap");
        MemcacheManager.getInstance().add(Constant.CITY_MAP, cMap);
        MemcacheManager.getInstance().add(Constant.AREA_MAP, aMap);
        logger.info("省市区信息加载完成...end");
        
        logger.info("开始加载银行信息...start");
        List<BankInfo> bankInfoList = bankInfoService.queryBankInfoAll();
        MemcacheManager.getInstance().add(Constant.BANK_LIST, bankInfoList);
        logger.info("银行信息加载完成...end");
        
        logger.info("开始加载卡bin信息...start");
        List<CardBin> cardBinList = cardBinService.queryCardBinAll();
        if(null != cardBinList && cardBinList.size() > 0){
        	Map<String, Object> carBinMap = new HashMap<String, Object>();
        	for (CardBin cardBin : cardBinList) {
        		carBinMap.put(cardBin.getCardBin(), cardBin);
			}
        	
        	MemcacheManager.getInstance().add(Constant.CARD_BIN, carBinMap);
        }
        logger.info("卡bin信息加载完成...end");
        
        
        logger.info("启动执行---end");
    }  

}
