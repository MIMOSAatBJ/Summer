package com.doumob.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @time 2013-04-23
 * @author Killer
 * 
 */
public class DateUtil {
	public static String default_patten = "yyyy-MM";
	public static String long_patten = "yyyy-MM-dd HH:mm:ss";
	public static String full_patten = "yyyy-MM-dd HH:mm:ss SSS";
	public static String number_patten="yyyyMMdd";

	/**
	 * 获取当前时间标准值
	 * 
	 * @return
	 */
	public static String getStandarDate() {
		return formatDate(null, null);
	}

	/**
	 * 获取当前时间长类型值
	 * 精确到秒
	 * @return
	 */
	public static String getLongDate() {
		return formatDate(long_patten, null);
	}

	/**
	 * 获取当前时间精确到微秒
	 * 
	 * @return
	 */
	public static String getFullDate() {
		return formatDate(full_patten, null);
	}

	/**
	 * 将传入的date格式化format类型 所有缺省时返回当前时间的yyyy-MM-dd形式
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String formatDate(String format, Date date) {
		if (format == null || "".equals(format)) {
			format = default_patten;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String d = null;
		if (date == null) {
			d = sdf.format(new Date());
		} else {
			d = sdf.format(date);
		}
		return d;
	}
	/**
	 * 将传入的date格式化yyyy-MM-dd类型 
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(default_patten);
		String d = null;
		if (date == null) {
			d= "";
		} else {
			d = sdf.format(date);
		}
		return d;
	}

	/**
	 * 判断年份是否为闰年。
	 * 
	 * @param year
	 *            年份， 必须大于1900
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		boolean isLeapYear;
		if (year < 1900) {
			isLeapYear = false;
		} else {
			isLeapYear = (year % 4 == 0);
		}
		return isLeapYear;
	}
	
	/**
	 * 增加月份后的日期数并按format格式输出
	 * 
	 * @param countMoney
	 * @return
	 */
	public static String getDayAdd(String dateStr, int day,String format) {
		if(format==null||format.trim().equals("")){
			format=default_patten;
		}
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			Date date = df.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, day);
			return df.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 增加月份后的日期数并按format格式输出
	 * 
	 * @param countMoney
	 * @return
	 */
	public static Date getDayAdd(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 获取当前月的第一天
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String getFirstDay() {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
				default_patten);
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.MONTH, 1);// 当前月＋1，即下个月
		cal.set(Calendar.DATE, 1);// 将下个月1号作为日期初始值
		String currentMonth = df.format(cal.getTime());
		return currentMonth;
	}

	/**
	 * 获取下个月的最后一天
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String getLastDay() {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
				default_patten);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		String currentMonth = df.format(cal.getTime());
		return currentMonth;
	}

	/**
	 * 得到2个字符串日期之间的日期差,返回结果以秒为单位
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static Integer getOffMills(String beginTime, String endTime) {
		SimpleDateFormat dfs = new SimpleDateFormat(long_patten);

		try {
			Date begin = dfs.parse(beginTime);
			Date end = dfs.parse(endTime);
			Long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
			return between.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 得到2个字符串日期之间的日期差,返回结果以天为单位
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static Integer getOffDays(String beginTime, String endTime) {
		SimpleDateFormat dfs = new SimpleDateFormat(default_patten);
		try {
			Date begin = dfs.parse(beginTime);
			Date end = dfs.parse(endTime);
			Long between = (end.getTime() - begin.getTime()) / 1000 / 3600 / 24;
			return between.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取当前月的最后一个工作日 2013-5-6
	 */
	public static String getLastWorkDay() {
		//获得该月的最后一天
		String theday = DateUtil.getLastDay();
		//判断最后一天是否是工作日
		return theday;
	}

	/**
	 * 得到yyyy-MM-dd中的yyyy，MM，dd
	 * 
	 * @param Date
	 *            时间串
	 * @param part
	 *            要获取的部分(y代表是年,M是月,d是天)
	 * @return
	 */
	public static String getDatePart(Date date,String part){
		if("y".equalsIgnoreCase(part)){
			return formatDate("yyyy", date);
		}else if("M".equalsIgnoreCase(part)){
			return formatDate("MM", date);
		}else if("d".equalsIgnoreCase(part)){
			return formatDate("dd", date);
		}
		else{
			return formatDate(default_patten, date);
		}
	}

	/**
	 * 获取字符串日期的date类型
	 */
	public static Date getDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat(default_patten);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
		}
		return date;
	}
	
	/**
	 * 
	 *@2015-6-26<br>
	 *@autor:zhangH<br>
	 *@desc:获取传入时间月的实际天数<br>
	 *@param date 传入的时间
	 *@return
	 */
	public static int getActulDays(String date){
		Date d=getDate(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
}
