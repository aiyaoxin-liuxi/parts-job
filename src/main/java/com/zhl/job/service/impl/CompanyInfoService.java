package com.zhl.job.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.pub.util.file.HclientFileUtil;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.dao.ICompanyInfoDao;
import com.zhl.job.dao.IUserLogInfoDao;
import com.zhl.job.pojo.CompanyInfo;
import com.zhl.job.pojo.UserInfo;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.UserState;
import com.zhl.job.util.Constant;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.Stringer;

/**
 * 企业
  * @ClassName: CompanyInfoService
  * @author zhaotisheng	
  * @date 2017年3月14日 上午10:54:00
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
  * http://localhost:8081/parts-job/compayinfo/register
  * 
  * http://localhost:8081/parts-job/compayinfo/login
  * 
 */
@Service
@Transactional
public class CompanyInfoService {

	public static final String  USER_NAME="USER_NAME";
	
	private Logger logs = LoggerFactory.getLogger(CompanyInfoService.class);
	
	
	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userInfoService;
	
	@Resource
	private ICompanyInfoDao companyInfoDao;
	
	@Resource
	private IUserLogInfoDao userLogInfoDao;
	

	@Autowired
	private CommonService commonService;
	/**
	 * @param request 
	 * 
	  * register
	  *
	  * @Title: register
	  * @param @param userLogInfo
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	public Object register(UserLogInfo userLogInfo, HttpServletRequest request){
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
		
//		UserLogInfo user_db = userLogInfoDao.findByMobileAndType(userLogInfo);
//		if( !Stringer.isNullOrEmpty(user_db) ){
//			res.setSuccess(false);
//			res.setErrmsg("手机号已经注册");
//			return res.toJson();
//		}
		String name = "C_";
		if("01".equals(userLogInfo.getUserType())){
			name = "P_";
		}
		userLogInfo.setId(name+KeySn.getKey());
		userLogInfo.setCreatedate(new Date());
		userLogInfo.setIsdel(IsDel.CODE00.getCode());
		Stringer.encryptLogPwd(userLogInfo);//
		BigDecimal bigDecimal = new BigDecimal(0);
		userLogInfo.setTotal(bigDecimal);
		userLogInfo.setNoUseAmount(bigDecimal);
		userLogInfo.setUseAmount(bigDecimal);
		//userState
		userLogInfo.setUserState(UserState.STATE_ENABLE.getCode());
		request.getSession().setAttribute(Constant.LOGIN_USER_ID, userLogInfo.getId());
		request.getSession().setAttribute(Constant.LOGIN_USER_MOBILE, userLogInfo.getMobile());
		int i = userLogInfoDao.insertSelective(userLogInfo);
//		int i=userLogInfoDao.saveOne(userLogInfo);
		return Stringer.commonOperation(i,"注册",res);
	}
	
	

	/**
	 * @param request 
	  * 登陆
	  * @Title: login
	  * @param @param userLogInfo
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	public Object login(UserLogInfo userLogInfo, HttpServletRequest request) {
		String mobile = userLogInfo.getMobile();
		String pwd = userLogInfo.getLogPassword();
		
		ResponseEntity res = new ResponseEntity();
		if(Stringer.isNullOrEmpty(mobile) ||  Stringer.isNullOrEmpty(pwd)){
			res.setSuccess(false);
			res.setErrmsg("手机号或者密码不能为空");
			return res.toJson();
		}
		List<UserLogInfo> list = userLogInfoDao.selectByUserLogInfoState(userLogInfo);
		if(list.size()==0){
			res.setSuccess(false);
			res.setErrmsg("用户不存在");
			return res.toJson();
		}
		
 		if(list.size()==1){
			UserLogInfo userDb = list.get(0);
			String userState = userDb.getUserState();
			if(Stringer.isNullOrEmpty(userState)){
				res.setSuccess(false);
				res.setErrmsg("userState是空，请联系管理员");
				return res.toJson();
			}
			if(userState.equals(UserState.STATE_DISABLE.getCode())){
				res.setSuccess(false);
				res.setErrmsg("用户已经禁用，请联系管理员");
				return res.toJson();
			}
//			if(userLogInfo.getLogPassword().equals(userDb.getLogPassword())){
			String encryptLogPwd = Stringer.encryptLogPwd(userLogInfo);
			logs.info("登陆比对密码：encryptLogPwd ："+encryptLogPwd +" userDb.getLogPassword():"+userDb.getLogPassword());
			if(encryptLogPwd.equals(userDb.getLogPassword())){
				
				commonService.setLoginInfo(request,userDb);//放入
				logs.debug("登陆成功，放入 LOGIN_USER_MOBILE： "+ userLogInfo.getMobile() +"\t  LOGIN_USER_ID:"+userDb.getId() +" \t 放入的userType:"+userDb.getUserType());
				res.setSuccess(true);
				res.setData(userDb);
				res.setErrmsg("登陆成功");
			}else{
				res.setSuccess(false);
				res.setErrmsg("用户名或者密码错误");
			}
			
			return res.toJson();
		}else{
			logs.error("不应该有多个"+JsonUtil.toJson(userLogInfo));
			throw new RuntimeException("不应该有多个"+JsonUtil.toJson(userLogInfo));
		}
		
//		res.setSuccess(false);
//		res.setErrmsg("莫名的fail");
//		return res.toJson();
	}
	
	private void setPersonId(HttpServletRequest request, String userId) {
		
		UserInfo selectByUserId = userInfoService.selectByUserId(userId);
		if(!Stringer.isNullOrEmpty(selectByUserId)){
			request.getSession().setAttribute(Constant.LOGIN_PERSON_ID, selectByUserId.getPid());
			request.getSession().setAttribute(Constant.LOGIN_USER_ENTITY, selectByUserId);
			String realname = selectByUserId.getRealname();
			if(!Stringer.isNullOrEmpty(realname)){
				request.getSession().setAttribute(USER_NAME, realname);
			}
		}
	}





	/**
	 * 
	  * insertOne
	  * @Title: insertOne
	  * @param @param companyInfo
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	public Object insertOne(CompanyInfo companyInfo) {
		ResponseEntity res = new ResponseEntity();
		
		companyInfo.setCid(KeySn.getKey());
		companyInfo.setCreatedate(new Date());
		companyInfo.setIsdel(IsDel.CODE00.getCode());
		
		int i = companyInfoDao.insertOne(companyInfo);
		
		res.setData(companyInfo.getCid());
		return Stringer.commonOperation(i,"保存",res);
	}

	public CompanyInfo getCompanyInfoByCid(CompanyInfo companyInfo) {
		return companyInfoDao.getCompanyInfoByCid(companyInfo);
	}

	public Object updateOne(HttpServletRequest request, CompanyInfo companyInfo) {
		ResponseEntity res = new ResponseEntity();
		
		companyInfo.setUpdatedate(new Date());
		int i = companyInfoDao.updateOneByCid(companyInfo);
		if(i==1){
			request.getSession().setAttribute(Constant.LOGIN_COMPANYINFO_ENTITY, companyInfo);
		}
		res.setData(companyInfo.getCid());
		return Stringer.commonOperation(i,"修改",res);
	}

	public CompanyInfo getCompanyInfoByUserId(UserLogInfo userLogInfo) {
		return companyInfoDao.getCompanyInfoByUserId(userLogInfo);
	}
	public ResponseEntity upload(MultipartHttpServletRequest request, ResponseEntity res) {
		String myFileType = request.getParameter("myFileType");
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		String fileId =null;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			if(mpf.isEmpty()){
				continue;
			}
			// 生成文件名称,当前时间年月日+5位UUID
			String name = Stringer.getNow("yyyyMMdd")+UUID.randomUUID().toString().substring(30);
			String suffix = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
			if(!Stringer.isImgSuffix(suffix)){
				res.setSuccess(false);
				res.setErrmsg("上传失败,格式错误");
				return res;
			}
			String fileName = name+suffix;
			String path ="";
			try {
				String savePath = generateFileSavePath();
			    path = Constant.REPOSITORY_IMG+savePath;
				File file = new File(path);
				if(!file.exists()){
					file.mkdirs();
				}
				mpf.transferTo(new File(path+File.separator+fileName));
				//上传文件服务器
				String uploadFileMethod = HclientFileUtil.uploadFileMethod(myFileType,new File(path+fileName));
				logs.info("###>>>> upload "+fileName+" to "+path+" success!");
				//返回文件路径
				fileId=Constant.IMAGE_SERVER_URL_LOAD + uploadFileMethod;
				//del
				delFile(new File(path+fileName));
			} catch (Exception e) {
				fileId="";
				logs.error("upload "+fileName+" to "+path+" ERROR!");
				e.printStackTrace();
			}
		}
		if(Stringer.isNullOrEmpty(fileId)){
			res.setSuccess(false);
			res.setErrmsg("上传失败");
			return res;
		}
		res.setSuccess(true);
		res.setErrmsg("上传成功");
		res.setData(fileId);
		return res;
	}
	private void delFile(File file2) {
		if(file2.exists()){
			logs.info("###>>>> 文件已经存在");
			file2.delete();
			logs.info("###>>>> {} 文件已经删除");
		}
	}



	/**
	 * 生成文件存放路径:
	 * 当前年\当前月\ 例:2015\12\
	 * @param 
	 * @return
	 */
	public static String generateFileSavePath() {
		String path = Stringer.getNow("yyyy"+File.separator+"MM")+File.separator;
		return path;
	}
	
	public PageInfo<CompanyInfo> queryCompanyInfoPageForManager(Map<String, Object> map,Integer pageNo,Integer pageSize){
		PageHelper.startPage(pageNo, pageSize);
	    List<CompanyInfo> list = companyInfoDao.queryCompanyInfoPageForManager(map);
	    //用PageInfo对结果进行包装
	    PageInfo<CompanyInfo> page = new PageInfo<CompanyInfo>(list);
	    return page;
	}
}
