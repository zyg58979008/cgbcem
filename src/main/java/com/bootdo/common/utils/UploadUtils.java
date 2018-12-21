package com.bootdo.common.utils;

import org.apache.fop.util.LogUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadUtils {

    /**
     * 判断身份证格式
     *
     * @param idNum
     * @return
     */
    public static boolean isIdNum(String idNum) {

        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");

        if (!idNumPattern.matcher(idNum).matches())
            return false;

        int year = 0;
        int month = 0;
        int day = 0;

        if (idNum.length() == 15) {

            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{2})(\\d{2})(\\d{2}).*");

            Matcher birthDateMather = birthDatePattern.matcher(idNum);

            if (birthDateMather.find()) {

                year = Integer.valueOf("19" + birthDateMather.group(1));
                month = Integer.valueOf(birthDateMather.group(2));
                day = Integer.valueOf(birthDateMather.group(3));

            }

        } else if (idNum.length() == 18) {


            Pattern birthDatePattern = Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");

            Matcher birthDateMather = birthDatePattern.matcher(idNum);

            if (birthDateMather.find()) {

                year = Integer.valueOf(birthDateMather.group(1));
                month = Integer.valueOf(birthDateMather.group(2));
                day = Integer.valueOf(birthDateMather.group(3));
            }

        }

        Calendar cal = Calendar.getInstance();

        int currentYear = cal.get(Calendar.YEAR);

        if (year <= currentYear - 100 || year > currentYear)
            return false;
        if (month < 1 || month > 12)
            return false;


        int dayCount = 31;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dayCount = 31;
                break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    dayCount = 29;
                    break;
                } else {
                    dayCount = 28;
                    break;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                dayCount = 30;
                break;
        }


        if (day < 1 || day > dayCount)
            return false;

        return true;
    }
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }
    public static boolean checkDate(String tax) {
        //这个正则匹配的是日期格式为:yyyy/MM/dd或者yyyy-MM-dd
        String rexp = "^(((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))|"
                + "((\\d{2}(([02468][048])|([13579][26]))[\\/]((((0?[13578])|(1[02]))[\\/]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\/]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\/]((((0?[13578])|(1[02]))[\\/]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\/]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/]((0?[1-9])|(1[0-9])|(2[0-8])))))))";
        Pattern p = Pattern.compile(rexp);
        Matcher m = p.matcher(tax);
        boolean isMatch = m.matches();
        return isMatch;
    }
    public static boolean checkTax(String tax) {
        String regex="^[A-Z0-9]{15}$|^[A-Z0-9]{17}$|^[A-Z0-9]{18}$|^[A-Z0-9]{20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(tax);
        boolean isMatch = m.matches();
        return isMatch;
    }

    public static Calendar formatDate(String sDate) {
        int i= sDate.split("-").length;
        if(i!=3){
            return null;
        }
        int y,m,d=0;
        String year=sDate.split("-")[0];
        String month=sDate.split("-")[1];
        String day=sDate.split("-")[2];
        if(isInteger(year)){
            y= Integer.parseInt(year);
        }else {
            return null;
        }
        if(isInteger(month)){
            m= Integer.parseInt(month);
        }else {
            return null;
        }
        if(isInteger(day)){
            d= Integer.parseInt(day);
        }else {
            return null;
        }
        Calendar c=Calendar.getInstance();
        c.set(y,m-1,d);
        return c;
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    public static Double format(Double num){
        BigDecimal b = new BigDecimal(num);
        num = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return num;
    }

    public static boolean checkHas(String room, List<String> strings) {
        boolean b=false;
        for(String s:strings){
            if(s.contains(room)){
                b=true;
                break;
            }
        }
        return b;
    }
    public static String checkEquals(String room, List<String> strings) {
        String b=null;
        for(String s:strings){
            if(s.contains(room)){
                b=s;
                break;
            }
        }
        return b;
    }
    public static boolean checkHasCode(String room, List<String> strings) {
        boolean b=false;
        for(String s:strings){
            if(s.equals(room)){
                b=true;
                break;
            }
        }
        return b;
    }
    public static int getMonths(Calendar c) {
        int cYear=c.get(Calendar.YEAR);
        int cMonth=c.get(Calendar.MONTH);
        return cYear*12+cMonth;
    }
}
