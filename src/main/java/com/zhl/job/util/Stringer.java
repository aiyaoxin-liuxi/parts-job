package com.zhl.job.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhl.job.interceptor.anno.NoAuth;
import com.zhl.job.interceptor.anno.NoAuthAll;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.pojo.enums.trans.Type;

/**
 * 
 *
 * <strong>Stringer </strong>. <br> 
 * <strong>Description :  封装一些和本框架相关的工具</strong> <br>
 * <strong>Create on : 2016年11月28日 上午10:52:29</strong>. <br>
 * <p>
 * <strong>Copyright (C) zhl Co.,Ltd.</strong> <br>
 * </p>
 * @author zts zhaotisheng@qq.com <br>
 * @version <strong>zhl-0.1.0</strong> <br>
 * <br>
 * <strong>修改历史: .</strong> <br>
 * 修改人 修改日期 修改描述<br>
 * Copyright ©  zhl by zts Inc. All Rights Reserved
 * -------------------------------------------<br>
 * <br>
 * <br>
 */
public class Stringer {
	private static Logger logs = LoggerFactory.getLogger(Stringer.class);

	public static String nullToEmpty(String string) {
		return isNullOrEmpty(string) ? "" : string;
	}
	/**
	 * 
	 * @desc toUperCaseFirstchar 大写第一个字母
	 * @author by zts 2016年11月25日下午7:54:36
	 * @param 
	 * String
	 * @exception
	 * @since  0.1.0
	 */
	public static  String toUperCaseFirstchar(String name) {
		if(isNullOrEmpty(name)){
			return null;
		}
		return name.substring(0,1).toUpperCase()+""+name.substring(1,name.length());
	}
	
	/**
	 * @author by zts Aug 21, 2015
	 *
	 * @desc 判断某对象是否为空.
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {

		boolean result = true;
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			result = (obj.toString().trim().length() == 0) || obj.toString().trim().equals("null");
		} else if (obj instanceof Collection) {
			result = ((Collection<?>) obj).size() == 0;
		} else if (obj instanceof Map) {
			result = ((java.util.Map<?,?>) obj).isEmpty();
		}else {
			result = ((obj == null) || (obj.toString().trim().length() < 1)) ? true : false;
		}
		return result;
	}

	
	/**
	 * 
	 * @desc checkNotNull(指定实体的属性不能为空)
	 * 
	 * 目前支持string参数，有空可以扩展
	 * @author by zts 2016年11月28日上午10:54:48
	 * @param 
	 * void
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @exception
	 * @since  0.1.0
	 * 
	 * 
	 * 
	 */
	public  static <T> void checkNotNull( T request, Object... obj) throws Exception {
		int len = obj.length;
		if(isNullOrEmpty(request) || isNullOrEmpty(obj) || !(len>=1))return ;		
		Class<? extends Object> c = request.getClass();
		for(int i=0;i<len;i++){
			Object o = obj[i];
			if(o instanceof java.lang.String){
				String s=(String)o;
				Object v = c.getMethod("get"+toUperCaseFirstchar(s)).invoke(request);
				if(isNullOrEmpty(v)){
						throw new RuntimeException(o+"不能为空");
				}
			}else{
				throw new RuntimeException("请扩展其他方法在使用");
			}
		}
	}


	public static boolean isURL(String str_url){  
        String strRegex = "^((https|http|ftp|rtsp|mms)://)"   
        + "(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@   
        + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184   
        + "|" // 允许IP和DOMAIN（域名）  
        + "(([0-9a-zA-Z_!~*'()-]+\\.)*" // 域名- www.   
        + "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\\." // 二级域名   
        + "[a-zA-Z]{2,6})|([0-9a-zA-Z]{1,}))" // first level domain- .com or .museum 
        + "(:[0-9]{1,5})?" // 端口- :80   (爱农给的是5位的"http://27.115.49.122:38280/bas/FrontTrans"; 20160803修改)
        + "((/?)|" // a slash isn't required if there is no file name   
        + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$"; 
        Pattern pattern = Pattern.compile(strRegex);
        Matcher m=pattern.matcher(str_url);  
        return m.matches();
    }

	public static boolean isMobile(String mobile) {
		Pattern compile = Pattern.compile("^((13[0-9])|(15[0-9])|147|(17[0-9])|(18[0-9]))[0-9]{8}$");
		Matcher matcher = compile.matcher(mobile);
		return matcher.matches();
	} 
	//数字
	public static boolean isDigit(String str) {
		Pattern compile = Pattern.compile("^\\d+$");
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	//身份证号（simple）
	public static boolean isIDNo(String str) {
		Pattern compile = Pattern.compile("(^\\d{18}$)|(^\\d{15}$)");
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	// 验证特殊字符
	public static boolean specialCharacter(String str) {
		Pattern compile = Pattern.compile("[`#$^&*()+=|{}\\[\\]<>/~@#￥……&*——+|{}]");
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	//是否包含特殊字符，包含返回true
	public static boolean haveSpecialCharacter(String str) {
		Pattern compile = Pattern.compile("[`#$^&*()+=|{}\\[\\]<>/~@#￥……&*——+|{}]");
		Matcher matcher = compile.matcher(str);
		return matcher.find();
	}
	
	// 金额，小数点后两位
	public static boolean isDouble(String str) {
		Pattern compile = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	
	public static void main(String[] args) {
//		System.out.println("he");
		System.out.println(isDigit("13d193"));
		BigDecimal b = new BigDecimal("1");
		System.out.println(b);
		System.out.println(b.doubleValue()*100);
	}
	
	
	
	public static Object commonOperation(int i, String msg, ResponseEntity res) {
		if(i==1){
			res.setErrmsg(msg+"成功");
			res.setSuccess(true);
			return res.toJson();
		}
		res.setErrmsg(msg+"失败");
		res.setSuccess(false);
		return res.toJson();
	}

	//获取交易前缀
	public static String getPrefixTranNo(String transTyoe) {
		Type parseOf = Type.parseOf(transTyoe);
		if(!isNullOrEmpty(parseOf)){
			return parseOf.getPrefixTranNo()+"_";
		}
		return "";
	}

		
	public static void setDoingState(String interfaceReturnCode, String interfaceReturnMsg, ResponseEntity res) {
		setResState(false,State.DOING.getCode(),  interfaceReturnCode,  interfaceReturnMsg,  res,null);
		ResponseEntity data = (ResponseEntity) res.getData();
		res.setErrmsg(data.getErrmsg());
		
	}
	public static void setMySpecialState(String mystate,String interfaceReturnCode, String interfaceReturnMsg, ResponseEntity res) {
		setResState(false,mystate,  interfaceReturnCode,  interfaceReturnMsg,  res,null);
		ResponseEntity data = (ResponseEntity) res.getData();
		res.setErrmsg(data.getErrmsg());
		
	}

	public static void setFailState(String state, String interfaceReturnCode, String interfaceReturnMsg, ResponseEntity res) {
		setResState(false,state,  interfaceReturnCode,  interfaceReturnMsg,  res,null);
		ResponseEntity data = (ResponseEntity) res.getData();
		res.setErrmsg(data.getErrmsg());//成功覆盖前台显示消息
	}
	
	public static void setFailState( String interfaceReturnCode, String interfaceReturnMsg, ResponseEntity res) {
		setResState(false,State.FAIL_99.getCode(),  interfaceReturnCode,  interfaceReturnMsg,  res,null);
		ResponseEntity data = (ResponseEntity) res.getData();
		res.setErrmsg(data.getErrmsg());//失败覆盖前台显示消息
	}

	

	public static void setSuccessState( String interfaceReturnCode, String interfaceReturnMsg, ResponseEntity res) {
		setResState(true,State.SUCCESS_66.getCode(),  interfaceReturnCode,  interfaceReturnMsg,  res,null);
	}
	public static void setSuccessState(String interfaceReturnCode, String interfaceReturnMsg, ResponseEntity res, Object data) {
		setResState(true,State.SUCCESS_66.getCode(),  interfaceReturnCode,  interfaceReturnMsg,  res,data);
	}
	private static void setResState(boolean b, String state, String interfaceReturnCode, String interfaceReturnMsg, ResponseEntity res,Object data) {
		res.setSuccess(b);
		res.setErrcode(state);
		
		ResponseEntity resRet=new ResponseEntity();
		resRet.setErrcode(interfaceReturnCode);
		resRet.setErrmsg(interfaceReturnMsg);
		resRet.setData(data);
		res.setData(resRet);
	}

	public static String fromYuanToFen(final String yuan) {  
        String fen = "";  
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");  
        Matcher matcher = pattern.matcher(yuan);  
        if (matcher.matches()) {  
            try {  
                NumberFormat format = NumberFormat.getInstance();  
                Number number = format.parse(yuan);  
                double temp = number.doubleValue() * 100.0;  
                // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012  
                format.setGroupingUsed(false);  
                // 设置返回数的小数部分所允许的最大位数  
                format.setMaximumFractionDigits(0);  
                fen = format.format(temp);  
            } catch (ParseException e) {  
                e.printStackTrace();  
            }  
        }else{  
//        	logger.info("元转分---参数格式不正确!");  
        }  
        return fen;  
    }  
	
	
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return (returnValue);
	}
	public static String getNow(String format) {
		return format(new Date(), format);
	}
	
	// 加密登陆密码
	public static String encryptLogPwd(UserLogInfo userLogInfo) {
		String logPassword = userLogInfo.getLogPassword();
		if(isNullOrEmpty(logPassword)){
			return null;
		}
		String encrypt = MD5.encrypt(logPassword);
		userLogInfo.setLogPassword(encrypt);
		return encrypt;
	}
	
	// 加密登陆密码
    public static String encryptLogPwd(String logPassword) {
        if(isNullOrEmpty(logPassword)){
            return null;
        }
        String encrypt = MD5.encrypt(logPassword);
        return encrypt;
    }

	//加密支付密码
	public static String encryptPayPwd(UserLogInfo userLogInfo) {
		String payPassword = userLogInfo.getPayPassword();
		if(isNullOrEmpty(payPassword)){
			return null;
		}
		String encrypt = MD5.encrypt(payPassword);
		userLogInfo.setPayPassword(encrypt);
		return encrypt;
	}

	//加密支付密码
	public static String encryptPayPwd(String payPassword) {
		if(isNullOrEmpty(payPassword)){
			return null;
		}
		String encrypt = MD5.encrypt(payPassword);
		return encrypt;
	}

	public static <T> void  setPageInfo(Model model,  PageInfo<T> pageInfo) {
		model.addAttribute("appList", pageInfo.getList());
		model.addAttribute("pageNo", pageInfo.getPageNum());
		model.addAttribute("pageSize", pageInfo.getPageSize());
		model.addAttribute("total", pageInfo.getTotal());
		model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
		model.addAttribute("pages", pageInfo.getPages());
		
		model.addAttribute("prePage", pageInfo.getPrePage());
		model.addAttribute("nextPage", pageInfo.getNextPage());
		logs.debug("##>>>pageInfo:"+pageInfo.toString());
	}
	
	public static  String replaceChar(String str) {
		
		return str.replaceAll("\\.", "/");
	}	

	//过滤，封装
	public static void filterAndInstance(List<String> packageNames2, Map<String, String> handMapping2) throws Exception {
			if(packageNames2.size()<=0){
				return ;
			}
			for (String className : packageNames2) {
				Class<?> c = Class.forName(className.replaceAll(".class", ""));
				if(c.isAnnotationPresent(Controller.class)){
					RequestMapping requestMappingAnnotation = c.getAnnotation(RequestMapping.class);
					NoAuthAll noAuthAllAnnotation = c.getAnnotation(NoAuthAll.class);
					if(!Stringer.isNullOrEmpty(noAuthAllAnnotation)){//
						continue;
					}else{
						String mapping = requestMappingAnnotation.value()[0];//目前大家都是用的第一个参数
						handlerMap(mapping,c,handMapping2);
					}
				}
			}
		}
		
		
		//uri封装
	public static void handlerMap(String mapping, Class<?> c, Map<String, String> handMapping2) {
			Method[] methods = c.getDeclaredMethods();
			for(Method m:methods){
				if(m.isAnnotationPresent(NoAuth.class)){
					continue;
				}else{
					RequestMapping requestMappingAnnotation = m.getAnnotation(RequestMapping.class);
					if(!Stringer.isNullOrEmpty(requestMappingAnnotation)){
						String[] value = requestMappingAnnotation.value();
//						RequestMethod[] method = requestMappingAnnotation.method();method[0].name()//questMethod.POST;
						ResponseBody responseBodyAnnotation = m.getAnnotation(ResponseBody.class);
//						logs.debug(mapping+"###>>> 类，方法:"+Arrays.toString(value));
						if(!Stringer.isNullOrEmpty(value) && value.length>0){
							handMapping2.put(mapping+""+value[0], responseBodyAnnotation==null?"GETORPOST":"AJAX");
						}
					}
					
				}
			}
		}
	public static boolean isFixedTelephone(String str) {
		Pattern compile = Pattern.compile("^[0-9-]+$");
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	public static boolean isImgSuffix(String suffix) {
		Pattern compile = Pattern.compile("^\\.(jpg|gif|png|bmp|jpeg|JPG|GIF|PNG|BMP|JPEG)$");
		Matcher matcher = compile.matcher(suffix);
		return matcher.matches();
	}
}
