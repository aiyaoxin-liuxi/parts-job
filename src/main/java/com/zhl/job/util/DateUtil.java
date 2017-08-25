package com.zhl.job.util;
/*
 * DateConverter.java
 * Copyright org.javaplus, Inc. All rights reserved.
 * org.javaplus PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.pub.util.date.DateConverter;

/**
 * 日期的转换
 */
public class DateUtil extends DateConverter{

	public static Date getDateToDay(String style){
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		String d = sdf.format(new Date());
		return string2Date(d);
	}
	
	/**
   	 * 获取？天以前时间
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static Date getDateBeforeDay(int day) throws ParseException{
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
   		Calendar curr = Calendar.getInstance();
   		curr.set(Calendar.DATE,curr.get(Calendar.DATE) - day);
//   		Date date = curr.getTime();
   		Date date = f.parse(f.format(curr.getTime()));
   		return date;
   	}
   	
   	/**
   	 * 获取？天以后时间
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static Date getDateNextDay(int day) throws ParseException{
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
   		Calendar curr = Calendar.getInstance();
   		curr.set(Calendar.DATE,curr.get(Calendar.DATE) + day);
//   		Date date = curr.getTime();
   		Date date = f.parse(f.format(curr.getTime()));
   		return date;
   	}
   	
   	/**
	 * 对比日期是否为当天
	 * @param date
	 */
	public static boolean ContrastDay(Date date) {
		boolean reB = false;
		if(date != null && !"".equals(date)){
			Date d = new Date();
			String d1 = DateConverter.date2String(d, "yyyy-MM-dd");
			String d2 = DateConverter.date2String(date, "yyyy-MM-dd");
			if(DateConverter.date2String(d, "yyyy-MM-dd").equals(DateConverter.date2String(date, "yyyy-MM-dd"))){
				reB = true;
			}
		}
		return reB;
	}
	
	/**
	 * 对比日期是否过期(当天也算过期)
	 * @param date
	 * @throws ParseException 
	 * true:沒过期
	 * false：过期
	 */
	public static boolean ContrastBeforeDayAndNowDay(Date date){
		boolean reB = false;
		if(date != null && !"".equals(date)){
			Date d = new Date();
			String d1 = DateConverter.date2String(d, "yyyy-MM-dd");
			String d2 = DateConverter.date2String(date, "yyyy-MM-dd");
			long l = getMillisDiff(string2Date(date2String(d, "yyyy-MM-dd")), string2Date(date2String(date, "yyyy-MM-dd")));
			if(l < 0){
				reB = true;
			}
		}
		return reB;
	}
	
	/**
	 * 对比日期是否过期(当天不算过期)
	 * @param date
	 * @throws ParseException 
	 */
	public static boolean ContrastBeforeDay(Date date){
		boolean reB = false;
		if(date != null && !"".equals(date)){
			Date d = new Date();
			String d1 = DateConverter.date2String(d, "yyyy-MM-dd");
			String d2 = DateConverter.date2String(date, "yyyy-MM-dd");
			long l = getMillisDiff(string2Date(date2String(d, "yyyy-MM-dd")), string2Date(date2String(date, "yyyy-MM-dd")));
			if(l <= 0){
				reB = true;
			}
		}
		return reB;
	}
	

	/**
	 * 根据起始日期和结束日期获取区间全日期
	 * @param date
	 * @throws ParseException 
	 */
	public static List<Date> getListAll(Date startDate, Date endDate){
		
		List<Date> lDate = new ArrayList<Date>();  
        lDate.add(startDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(startDate);
        boolean bContinue = true;
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }
        if(lDate.size() == 1){
			if(!endDate.equals(startDate)){
				lDate.add(endDate);// 把结束时间加入集合  
			}
		}
        return lDate;
	}
	
//	/**
//	 * 根据起始时间和结束时间获取过期的时间
//	 * @return
//	 */
//	public static List<Date> screenOverdueDate(Date startDate, Date endDate){
//		List<Date> list = getListAll(startDate, endDate);
//		// 首先刨除过期的
//		for(int i = 0; i < list.size(); i++){
//			if(!ContrastBeforeDay(list.get(i))){// 未过期
//				list.remove(i);
//			}
//		}
//		return list;
//	}
	
	
	
	public static void main(String[] args) {
		System.out.println(ContrastDay(new Date()));
	}
}
