package gkyt.commons.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期时间处理支持类
 * @author alin
 *
 */
public final class DateSupport {

	/**
	 * 获取系统当前的年份
	 * @return
	 */
	public static Integer getCurrentYear(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 获取指定日期的年份
	 * @return
	 */
	public static Integer getYearOf(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 获取指定日期的月份
	 * @return
	 */
	public static Integer getMonthOf(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}
	
	/**
	 * 获取指定日期的月份中某一天
	 * @return
	 */
	public static Integer getDayOf(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取指定日期所属的季度
	 * @return
	 */
	public static Integer getSeasonOfYear(Date date){
		if(date == null){
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH)+1;
		if(month>=1 && month<=3){
			return 1;
		}
		if(month>3 && month<=6){
			return 2;
		}
		if(month>6 && month<=9){
			return 3;
		}
		if(month>9 && month<=12){
			return 4;
		}
		return 0;
	}
	
	/**
	 * 解析日期字符串，转换成相应的日期对象
	 * @param dateString
	 * 		日期格式字符串，支持格式：yyyy/MM/dd、yyyy-MM-dd、yyyy.MM.dd
	 * @return
	 */
	public static Date parseDateString(String dateString){
		DateFormat format = null;
		try{
			if(dateString.indexOf("/")!=-1){
				//时期格式为yyyy/MM/dd
				format = new SimpleDateFormat("yyyy/MM/dd");
				return format.parse(dateString);
			}else if(dateString.indexOf("-")!=-1){
				format = new SimpleDateFormat("yyyy-MM-dd");
				return format.parse(dateString);
			}else if(dateString.indexOf(".")!=-1){
				format = new SimpleDateFormat("yyyy.MM.dd");
				return format.parse(dateString);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 解析日期字符串，转换成相应的日期对象
	 * @param dateString
	 * 		日期格式字符串，支持格式：yyyy/MM/dd、yyyy-MM-dd、yyyy.MM.dd
	 * @return
	 */
	public static Date parseDatetimeString(String datetimeString){
		DateFormat format = null;
		try{
			if(datetimeString.indexOf("/")!=-1){
				//时期格式为yyyy/MM/dd
				format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				return format.parse(datetimeString);
			}else if(datetimeString.indexOf("-")!=-1){
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return format.parse(datetimeString);
			}else if(datetimeString.indexOf(".")!=-1){
				format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
				return format.parse(datetimeString);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将日期对象转换成相应的日期字符串,仅用于中文格式
	 * @param date
	 * 		日期对象
	 * @return
	 */
	public static String parseDateChineseString(Date date){
		DateFormat format = null;
		try{
			format = new SimpleDateFormat("yyyy年MM月dd日");
			return format.format(date);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 将日期对象转换成相应的日期字符串
	 * @param date
	 * 		日期对象
	 * @param formatStr
	 * 		日期字符串形式，支持格式：yyyy/MM/dd和yyyy-MM-dd
	 * 			 默认格式为：yyyy-MM-dd
	 * @return
	 * 		String
	 */
	public static String parseDate(Date date,String formatStr){
		if(date ==null){
			return null;
		}
		
		if(StringUtils.isBlank(formatStr)){
			formatStr = "yyyy-MM-dd";
		}
		
		
		DateFormat format = new SimpleDateFormat(formatStr);
		try{
			return format.format(date);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取特定格式的时间戳
	 * @return
	 */
	public static String getTimestamps(){
		
		try{
				DateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
				return format.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 获取日期时间的日期部分字符串
	 * @return
	 */
	public static String getDateOfTimestamps(){
		try{
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			return format.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取日期时间的时间部分字符串
	 * @return
	 */
	public static String getTimeOfTimestamps(){
		try{
			DateFormat format = new SimpleDateFormat("HHmmss");
			return format.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 计算两个日期之间的天数
	 * @param start
	 * 		开始日期
	 * @param end
	 * 		结束日期
	 * @return
	 */
	public static long caculateDays(Date start,Date end){
		Date newStart = null;
		Date newEnd = null;
		if(start.after(end)){
			newStart = end;
			newEnd = start;
		}else{
			newStart = start;
			newEnd = end;
		}
		
		Long startMilis = newStart.getTime();
		Long endMilis = newEnd.getTime();
		long days = (endMilis-startMilis)/(1000*60*60*24);
		return days;
	}
	

	/**
	 * 计算两个日期之间的年数
	 * @param start
	 * 		开始日期
	 * @param end
	 * 		结束日期
	 * @return
	 */
	public static long caculateYears(Date start,Date end){
		Date newStart = null;
		Date newEnd = null;
		if(start.after(end)){
			newStart = end;
			newEnd = start;
		}else{
			newStart = start;
			newEnd = end;
		}
		
		Long startMilis = newStart.getTime();
		Long endMilis = newEnd.getTime();
		long  years= (endMilis-startMilis)/(1000*60*60*24)/365;
		return years;
	}
	
	
	public static Date getDateAfter(Date current,int days){
	   Calendar calendar=Calendar.getInstance();   
	   calendar.setTime(current); 
	   //System.out.println(calendar.get(Calendar.DAY_OF_MONTH));//今天的日期 
	   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+days);//让日期加1  
	  // System.out.println(calendar.get(Calendar.DATE));//加1之后的日期Top 
	   return calendar.getTime();
	}
	
	/**
	 * 获取指定年月的天数
	 * @param year
	 * 		年份：1984
	 * @param month
	 * 		月份：1~12
	 * @return
	 */
	public static int getDaysOfMonth(int year,int month){
		//计算年份是否为闰年
		switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if((year%4==0) || (year%100==0 && year%400!=0)){
					return 29;
				}else{
					return 28;
				}
		}
		return 0;
	}
	
	public static void main(String[] args){	
		System.out.println(DateSupport.getMonthOf(new Date()));
	}
}