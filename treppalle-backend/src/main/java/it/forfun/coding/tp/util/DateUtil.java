package it.forfun.coding.tp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

//    public static final String dateDayFormatWithMinus = "dd-MMM-yy";
    public static final String dateDayFormat = "yyyy/MM/dd";
    public static final String dateDayTimeFormat = "yyyy/MM/dd HH:mm";

    public static Date addHoursToJavaUtilDate(Date date, Integer hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    /**
     * Convert the date from string into the PAM format YYYY/mm/dd HH:mm:ss
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date stringToDateTime(String date) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat(dateDayTimeFormat);
//        FastDateFormat dateFormat = FastDateFormat.getInstance(dateDayTimeFormat);
        return dateFormat.parse(date);
    }

    public static Date stringToDate(String date) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat(dateDayFormat);
//        FastDateFormat dateFormat = FastDateFormat.getInstance(dateDayTimeFormat);
        return dateFormat.parse(date);
    }

    public static String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat(dateDayTimeFormat);
//        FastDateFormat dateFormat = FastDateFormat.getInstance(dateDayTimeFormat);
        return dateFormat.format(date);
    }



}
