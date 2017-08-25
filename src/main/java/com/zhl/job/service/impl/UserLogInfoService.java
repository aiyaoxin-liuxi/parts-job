package com.zhl.job.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.dao.IUserLogInfoDao;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.Stringer;
@Service("userLogInfoService")
@Transactional
public class UserLogInfoService implements IUserLogInfoService {

	private Logger logs = LoggerFactory.getLogger(UserLogInfoService.class);
	
	@Resource
	private IUserLogInfoDao userLogInfoDao;
//	@Resource
//	private IAccountflowDao accountflowDao;
	
	@Override
	public int insertSelective(UserLogInfo userLogInfo) {
		return userLogInfoDao.insertSelective(userLogInfo);
	}

	@Override
	public int updateByPrimaryKeySelective(UserLogInfo userLogInfo) {
		return userLogInfoDao.updateByPrimaryKeySelective(userLogInfo);
	}

	@Override
	public boolean selectByUserLogInfoState(UserLogInfo userLogInfo) {
		List<UserLogInfo> infos =  userLogInfoDao.selectByUserLogInfoState(userLogInfo);
		if(infos.size()>=1){
			return true;
		}
		return false;
	}
	
	public UserLogInfo getByMobile(UserLogInfo userLogInfo){
		List<UserLogInfo> list =  userLogInfoDao.selectByUserLogInfoState(userLogInfo);
		if(Stringer.isNullOrEmpty(list)){
			logs.error("##>>>>> 没有找到用户：" +JsonUtil.toJson(userLogInfo));
		}
		if(list.size() ==1){
			return  list.get(0);
		}else{
			logs.error("##>>>>> 找到用户的数量不对：" +JsonUtil.toJson(userLogInfo) +" size:"+list.size());
		}
		return null;
	}

	@Override
	public Object register(UserLogInfo userLogInfo) {
		logs.debug(">>>>>register param："+JsonUtil.toJson(userLogInfo));
		String logPassword = userLogInfo.getLogPassword();
		ResponseEntity res = new ResponseEntity();
		if(Stringer.isMobile(logPassword)){
			res.setSuccess(false);
			res.setErrmsg("密码不能为空");
			return res.toJson();
		}
		//可以在这里校验密码的复杂度 .
		
		//数据库验重
		List<UserLogInfo> list = userLogInfoDao.selectByUserLogInfoState(userLogInfo);
		if( !Stringer.isNullOrEmpty(list) || list.size()>0 ){
			res.setSuccess(false);
			res.setErrmsg("手机号已经注册");
			return res.toJson();
		}
		String id = "P_"+KeySn.getKey();
		userLogInfo.setId(id);
		userLogInfo.setCreatedate(new Date());
		userLogInfo.setIsdel(IsDel.CODE00.getCode());
		userLogInfo.setTotal(new BigDecimal(0));
		userLogInfo.setUseAmount(new BigDecimal(0));
		int i = userLogInfoDao.insertSelective(userLogInfo);
		return Stringer.commonOperation(i,"注册",res);
	}

	@Override
	public UserLogInfo selectByPk(String id) {
		return userLogInfoDao.selectByPk(id);
	}

	@Override
	public PageInfo<UserLogInfo> queryUserLogInfoPage(Map<String, Object> map,
			Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
	    List<UserLogInfo> list = userLogInfoDao.queryUserLogInfoPage(map);
	    //用PageInfo对结果进行包装
	    PageInfo<UserLogInfo> page = new PageInfo<UserLogInfo>(list);
	    return page;
	}

}
