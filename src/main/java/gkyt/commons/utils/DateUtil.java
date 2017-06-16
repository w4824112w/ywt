package gkyt.commons.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author hk
 *
 */
public class DateUtil {
	
	/**
	 * 获取前一天
	 * 
	 * @param cl
	 * @return
	 */
	public static Date getBeforeDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	public static void main(String[] args) {
		Date date=new Date();
		System.out.println("date11------"+date);
		date = getBeforeDay(date);
		System.out.println("date22------"+date);
	}
}
