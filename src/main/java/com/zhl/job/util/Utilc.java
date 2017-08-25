package com.zhl.job.util;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.pub.util.security.MapKeyComparator;

//短信的排序
public class Utilc {

	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(
				new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}
	
	/**
	 * 隐藏手机号码
	 * @return
	 */
	public static String hideMobile(String mobile){
		if(null != mobile && !"".equals(mobile) && mobile.length() == 11){
			mobile = new StringBuilder().append(StringUtils.substring(mobile, 0, 3)).append("********").toString();
		}
		return mobile;
	}
}
