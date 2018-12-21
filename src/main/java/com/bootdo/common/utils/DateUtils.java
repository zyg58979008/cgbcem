package com.bootdo.common.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }
    public static int getMonth(Date start, Date end) {
        Calendar s=Calendar.getInstance();
        Calendar e=Calendar.getInstance();
        s.setTime(start);
        e.setTime(end);
        int a=(e.get(Calendar.YEAR)*12+e.get(Calendar.MONTH)+1);
        int b=(s.get(Calendar.YEAR)*12+s.get(Calendar.MONTH)+1);
        return a-b-1;
    }
    public static int getMonthF(Date start, Date end) {
        Calendar s=Calendar.getInstance();
        Calendar e=Calendar.getInstance();
        s.setTime(start);
        e.setTime(end);
        int a=(e.get(Calendar.YEAR)*12+e.get(Calendar.MONTH)+1);
        int b=(s.get(Calendar.YEAR)*12+s.get(Calendar.MONTH)+1);
        return a-b+1;
    }
    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }
    public static Double getMonths(Date start,Date end){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String s=sdf.format(start);
        String e=sdf.format(end);
        Double m= Double.valueOf(0);
        java.text.DecimalFormat   df=new   java.text.DecimalFormat("0.00");
        if(s.substring(0,8).equals(e.substring(0,8))){
            if(start.getDate()==1&&DateUtils.isLastDayOfMonth(end)){
                m= Double.valueOf(1);
            }else{
                m=Double.valueOf(df.format(Double.valueOf(end.getDate()+1-Double.valueOf(start.getDate()))/30));
            }
        }else {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            int months = DateUtils.getMonth(start,end);
            Double sMonth= Double.valueOf(0);
            Double eMonth= Double.valueOf(0);
            if(start.getDate()==1){
                sMonth= Double.valueOf(1);
            }else {

                Calendar lastDay=Calendar.getInstance();
                lastDay.setTime(start);
                int d=lastDay.getActualMaximum(Calendar.DAY_OF_MONTH)+1;
                Double day= Double.valueOf(d-start.getDate());
                sMonth= Double.valueOf(df.format(day/30));
            }
            if(DateUtils.isLastDayOfMonth(end)){
                eMonth=Double.valueOf(1);
            }else {
                eMonth=Double.valueOf(df.format((Double.valueOf(end.getDate())/30)));
            }
            m=months+sMonth+eMonth;
        }
        return m;
    }
    public static Double getMonths30(Date start,Date end){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String s=sdf.format(start);
        String e=sdf.format(end);
        Double m= Double.valueOf(0);
        java.text.DecimalFormat   df=new   java.text.DecimalFormat("0.00");
        if(s.substring(0,8).equals(e.substring(0,8))){
            if(start.getDate()==1&&DateUtils.isLastDayOfMonth(end)){
                m= Double.valueOf(1);
            }else{
                m=Double.valueOf(df.format(Double.valueOf(end.getDate()+1-Double.valueOf(start.getDate()))/30));
            }
        }else {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            int months = DateUtils.getMonth(start,end);
            Double sMonth= Double.valueOf(0);
            Double eMonth= Double.valueOf(0);
            if(start.getDate()==1){
                sMonth= Double.valueOf(1);
            }else {

                Calendar lastDay=Calendar.getInstance();
                lastDay.setTime(start);
                Double day= Double.valueOf(30-start.getDate()+1);
                if(lastDay.get(Calendar.DATE)==31){
                    day= Double.valueOf(1);
                }
                sMonth= Double.valueOf(df.format(day/30));
            }
            if(DateUtils.isLastDayOfMonth(end)){
                eMonth=Double.valueOf(1);
            }else {
                eMonth=Double.valueOf(df.format((Double.valueOf(end.getDate())/30)));
            }
            m=months+sMonth+eMonth;
        }
        return m;
    }
    public static int getMonthNum(Date start,Date end){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String s=sdf.format(start);
        String e=sdf.format(end);
        int num=0;
        if(s.substring(0,8).equals(e.substring(0,8))){
            num=1;
        }else {
            int year1=Integer.valueOf(s.substring(0,4));
            int year2=Integer.valueOf(e.substring(0,4));
            int month1=Integer.valueOf(s.substring(5,7));
            int month2=Integer.valueOf(e.substring(5,7));
            num=(year2*12+month2)-(year1*12+month1)+1;
        }
        return num;
    }
    public static int getMaxDay(Date startDate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String s=sdf.format(startDate);
        Calendar calendar = Calendar.getInstance();
        int year=Integer.parseInt(s.substring(0, 4));
        int month=Integer.parseInt(s.substring(5, 7)) -1;
        calendar.set(year,month,1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return maxDay+1;
    }
    //计算某个日期增加N个月的时间后的日期
    public static Date getLastDate(Date startDate,int n) throws ParseException {//传入开始日期以及间隔的月数
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(startDate));//取到年份值
        int month=Integer.parseInt(new SimpleDateFormat("MM").format(startDate))+n;//取到月份值
        int day=Integer.parseInt(new SimpleDateFormat("dd").format(startDate))-1;//取到天值
        if(month>12){
            int m = (int)Math.floor(month/12);
            year+=m;month=month-12*m;
        }
        if(day>28){
            if(month==2){
                if(year%400==0||(year %4==0&&year%100!=0)){
                    day=29;
                }else day=28;
            }else if((month==4||month==6||month==9||month==11)&&day==31)
            {
                day=30;
            }
        }
        String y = year+"";String m ="";String d ="";
        m=month+"";
        if(day == 0){
            day =day + 1;
            d = day+"";
            String end = y+"-"+m+"-"+d;
            long dif = sdf.parse(end).getTime()-86400*1000;//减一天
            Date dateEnd=new Date();
            dateEnd.setTime(dif);
            return dateEnd;
        }else{
            d = day+"";
            return sdf.parse(y+"-"+m+"-"+d);
        }
    }
    //日期加一天
    public static Date dayAddOne(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
    public static int getMaxDay(Calendar c){
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        Calendar d=Calendar.getInstance();
        d.set(year,month,1);
        return d.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    public static Calendar getMaxDate(Calendar c){
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        Calendar d=Calendar.getInstance();
        d.set(year,month,1);
        int day=d.getActualMaximum(Calendar.DAY_OF_MONTH);
        d.set(year,month,day);
        return d;
    }
    /**
     * http://www.cnblogs.com/0201zcr/p/5000977.html
     * date2比date1多的天数
     *
     * @param cal1
     * @param cal2
     * @return
     */
    public static int calcDayOffset(Calendar cal1, Calendar cal2) {
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {  //同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else {  //不是闰年

                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { //不同年
            return day2 - day1;
        }
    }
    public static ArrayList<String> getMonthBetween(Date minDate, Date maxDate, String type) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
        String s=sdf.format(minDate);
        String e=sdf.format(maxDate);
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(s));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DAY_OF_MONTH));

        max.setTime(sdf.parse(e));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DAY_OF_MONTH));

        Calendar curr = min;
        if(type.equals("1")){
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        }else if(type.equals("2")){
            Calendar min2 = Calendar.getInstance();
            min2.setTime(sdf.parse(s));
            min2.set(min2.get(Calendar.YEAR), min2.get(Calendar.MONTH),  min2.get(Calendar.DAY_OF_MONTH));
            Calendar curra = min2;
            curra.add(Calendar.MONTH, 2);
            while (curr.before(max)) {
                if(curra.before(max)){
                    result.add(sdf.format(curr.getTime())+"|"+sdf.format(curra.getTime()));
                    curr.add(Calendar.MONTH, 3);
                    curra.add(Calendar.MONTH, 3);
                }else{
                    result.add(sdf.format(curr.getTime())+"|"+sdf.format(max.getTime()));
                    break;
                }
            }
        }else if(type.equals("3")){
            Calendar min2 = Calendar.getInstance();
            min2.setTime(sdf.parse(s));
            min2.set(min2.get(Calendar.YEAR), min2.get(Calendar.MONTH),  min2.get(Calendar.DAY_OF_MONTH));
            Calendar curra = min2;
            curra.add(Calendar.MONTH, 11);
            while (curr.before(max)) {
                if(curra.before(max)){
                    result.add(sdf.format(curr.getTime())+"|"+sdf.format(curra.getTime()));
                    curr.add(Calendar.MONTH, 12);
                    curra.add(Calendar.MONTH, 12);
                }else{
                    result.add(sdf.format(curr.getTime())+"|"+sdf.format(max.getTime()));
                    break;
                }
            }
        }else if(type.equals("4")){
            Calendar min2 = Calendar.getInstance();
            min2.setTime(sdf.parse(s));
            min2.set(min2.get(Calendar.YEAR), min2.get(Calendar.MONTH), min2.get(Calendar.DAY_OF_MONTH));
            Calendar curra = min2;
            curra.add(Calendar.MONTH, 5);
            while (curr.before(max)) {
                if(curra.before(max)){
                    result.add(sdf.format(curr.getTime())+"|"+sdf.format(curra.getTime()));
                    curr.add(Calendar.MONTH, 6);
                    curra.add(Calendar.MONTH, 6);
                }else{
                    result.add(sdf.format(curr.getTime())+"|"+sdf.format(max.getTime()));
                    break;
                }
            }
        }
        return result;
    }
    public static ArrayList<String> getMonthBetweenTanxiao(Date minDate, Date maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        String s=sdf.format(minDate);
        String e=sdf.format(maxDate);
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(s));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH),1);

        max.setTime(sdf.parse(e));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH),2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }
    public static boolean monthIfIn(String date,String startDate,String endDate){
        int yearD= Integer.parseInt(date.substring(0,4));
        int monthD= Integer.parseInt(date.substring(5,7));
        int dayD= Integer.parseInt(date.substring(8,10));
        int yearS= Integer.parseInt(startDate.substring(0,4));
        int monthS= Integer.parseInt(startDate.substring(5,7));
        int dayS= Integer.parseInt(startDate.substring(8,10));
        int yearE= Integer.parseInt(endDate.substring(0,4));
        int monthE= Integer.parseInt(endDate.substring(5,7));
        int dayE= Integer.parseInt(endDate.substring(8,10));
        int total = yearD+monthD+dayD;
        int totalS = yearS+monthS+dayS;
        int totalE = yearE+monthE+dayE;
        return total <= totalE && total >= totalS;
    }
    public static void main(String[] a){
        /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar c=Calendar.getInstance();
        c.set(2018,1,16);
        System.out.println(sdf.format(c.getTime()));
        c.add(Calendar.MONTH,12);
        System.out.print(sdf.format(c.getTime()));*/
        String date = "2018-01-06";
        String startDate = "2017-12-06";
        String endDate = "2018-01-07";
        int yearD= Integer.parseInt(date.substring(0,4));
        int monthD= Integer.parseInt(date.substring(5,7));
        int dayD= Integer.parseInt(date.substring(8,10));
        int yearS= Integer.parseInt(startDate.substring(0,4));
        int monthS= Integer.parseInt(startDate.substring(5,7));
        int dayS= Integer.parseInt(startDate.substring(8,10));
        int yearE= Integer.parseInt(endDate.substring(0,4));
        int monthE= Integer.parseInt(endDate.substring(5,7));
        int dayE= Integer.parseInt(endDate.substring(8,10));
        int total = yearD*12+monthD+dayD;
        int totalS = yearS+monthS+dayS;
        int totalE = yearE+monthE+dayE;
        System.out.print(total+" "+totalS+" "+totalE);
       System.out.print(total <= totalE && total >= totalS);
    }
}
