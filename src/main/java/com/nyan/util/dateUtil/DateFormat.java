package com.nyan.util.dateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * @author DUONG_VAN_NHAN
 *
 */
public class DateFormat {
	private final static String DD_MM_YYYY ="dd/MM/yyyy";
	private static SimpleDateFormat simpleFormat;
	private static Calendar calender;
	
	/**
	 * 
	 * @param dt
	 * @param format
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public static String dateToString(Date dt, String format) throws IllegalArgumentException, Exception{
		String rs = null;
		if(dt == null){
			throw new IllegalArgumentException("date is null");
		}
		else if(format == null){
			throw new IllegalArgumentException("format is null");
		}else{
			try{
				simpleFormat = new SimpleDateFormat(format);
				if(simpleFormat != null){
					rs = simpleFormat.format(dt);
				}else{
					rs = "";
				}
			}catch (Exception e) {
				throw new Exception("Error format Date to String");
			}
			
		}
		return rs;
	}
	
	/**
	 * 
	 * @param dt
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public static String defaultDateToString(Date dt) throws IllegalArgumentException, Exception{
		String rs = null;
		if(dt ==null){
			throw new IllegalArgumentException("date is null");
		}else{
			try{
				simpleFormat = new SimpleDateFormat(DD_MM_YYYY);
				if(simpleFormat != null){
					rs = simpleFormat.format(dt);
				}
				else{
					rs = "";
				}
			}catch (Exception e) {
				throw new Exception(" Error format default");
			}
			
			
		}
		return rs;
	}
	
	/**
	 * 
	 * @param input
	 * @param format
	 * @return
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public static Date stringToDate(String input, String format) throws IllegalArgumentException, Exception{
		Date rs = null;
		if(input == null){
			throw new IllegalArgumentException("input is null");
		}else if(format == null){
			throw new IllegalArgumentException("format is null");
		}else{
			try{
				simpleFormat = new SimpleDateFormat(format);
				if(simpleFormat != null){
					rs = simpleFormat.parse(input);
				}
				
			}catch (Exception e) {
				throw new Exception("Error format String to Date");
			}
		}
		return rs;
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Date getCurentDate() throws Exception{
		Date rs= null;
		calender = Calendar.getInstance();
		if(calender != null){
			rs = calender.getTime();
		}
		return rs;
	}
	/**
	 * 
	 * @return
	 */
	public static String getCurentDay(){
		String rs = null;
		calender = Calendar.getInstance();
		if(calender != null){
			int dayOfMonth = calender.get(Calendar.DAY_OF_MONTH);
			rs = String.valueOf(dayOfMonth);
		}
		return rs;
	}
	/**
	 * 
	 * @return
	 */
	public static String getCurentMonth(){
		String rs = null;
		calender = Calendar.getInstance();
		if(calender != null){
			int month = calender.get(Calendar.MONTH);
			rs = String.valueOf(month);
		}
		return rs;
	}
	/**
	 * 
	 * @return
	 */
	public static String getCurentYear(){
		String rs = null;
		calender = Calendar.getInstance();
		if(calender != null){
			int year = calender.get(Calendar.YEAR);
			rs = String.valueOf(year);
		}
		return rs;
	}
	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception 
	 */
	public static int getDayFromDateToDate(Date fromDate, Date toDate) throws Exception {
		int numberDay=-1;
		if(fromDate == null){
			throw new IllegalArgumentException("from date is null");
		}
		else if(toDate == null){
			throw new IllegalArgumentException("to date is null");
		}else if(fromDate.after(toDate)){
			throw new Exception(" from date must small than to date");
		}else {
			Calendar cl = Calendar.getInstance();
			cl.setTime(fromDate);
			int fromDay = cl.get(Calendar.DAY_OF_MONTH);
			int fromMonth = cl.get(Calendar.MONTH);
			int fromYear = cl.get(Calendar.YEAR);
			cl.setTime(toDate);
			int toDay = cl.get(Calendar.DAY_OF_MONTH);
			int toMonth = cl.get(Calendar.MONTH);
			int toYear = cl.get(Calendar.YEAR);
			if(fromYear == toYear){
				if(fromMonth == toMonth){
					if(fromDay == toDay){
						numberDay = 0;
					}else{
						numberDay = toDay - fromDay;
					}
				}else{
					// lay ngay cuoi thang cua fromMonth
					Date dtFrom = getLastDayOfMonth(fromMonth, fromYear);
					cl.setTime(dtFrom);
					int day = cl.get(Calendar.DAY_OF_MONTH);
					numberDay = day - fromDay;
					for(int i = fromMonth+1; i <= toMonth-1; i++){
						
					}
						
				}
			}else{
				
			}
		}
		return numberDay;
	}
	
	/**
	 * 
	 * @param month
	 * @param year
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Date getLastDayOfMonth(int month, int year) throws IllegalArgumentException{
		Date dt = null;
		if(month == 0){
			throw new IllegalArgumentException("month is not 0");
		}else if (year == 0){
			throw new IllegalArgumentException("year is not 0");
		}else{
			Calendar cl = Calendar.getInstance();
			cl.set(year, month -1, 1);
			cl.set(Calendar.DATE, calender.getActualMaximum(Calendar.DATE));
			dt = cl.getTime();
			return dt;
		}
	}
	
	public static Date getFristDayOfMonth(int month, int year)throws IllegalArgumentException{
		Date dt = null;
		if(month == 0){
			throw new IllegalArgumentException("month is not 0");
		}else if (year == 0){
			throw new IllegalArgumentException("year is not 0");
		}else{
			Calendar cl = Calendar.getInstance();
			cl.set(year, month -1, 1);
			dt = cl.getTime();
			return dt;
		}
	}
}
