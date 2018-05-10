package cn.edu.nyist.util;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.edu.nyist.LogUtil.Logger;

/**
 * @author zhangsiqi
 */
public class DateTimeUtil {

    /**
     * joda-time
     */
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyyMMdd";
    public static final String EEEE = "EEEE";

    /**
     * str-->Date
     * 日期字符串根据传入格式, 返回Date对象
     *
     * @param dateTimeStr 日期字符串
     * @return 日期对象
     */
    public static Date strToDate(String format, String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * Date->str
     * Date对象根据传入格式转化为日期字符串
     *
     * @param date Date对象
     * @return 日期字符串
     */
    public static String dateToStr(String format, Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }

    /**
     * 获取当前日期
     *
     * @return 20180424
     */
    public static String getYyyyMmDd() {
        return dateToStr(YYYY_MM_DD, new Date());
    }

    public static boolean isBelong(String start, String end) {

        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now = null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse(start);
            endTime = df.parse(end);
            Logger.d("beginTime: {" + beginTime + "} end: { " + endTime + "} now:{ " + now +"}");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boolean flag = belongCalendar(now, beginTime, endTime);
        return flag;
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        //System.out.println(new Date());
        //System.out.println(DateTimeUtil.dateToStr(DateTimeUtil.HH_MM_SS, new Date()));
        //System.out.println(DateTimeUtil.strToDate("2010-01-01 11:11:11"));
        //System.out.println(UUID.randomUUID().toString());
        System.out.println(DateTimeUtil.dateToStr(DateTimeUtil.YYYY_MM_DD, new Date()));
        System.out.println(DateTimeUtil.dateToStr(DateTimeUtil.EEEE, new Date()));
    }

}
