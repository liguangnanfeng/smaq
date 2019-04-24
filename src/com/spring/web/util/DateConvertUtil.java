package com.spring.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * @Title: DateConvertUtil
 * @Description: 日期工具类
 * @author FL
 * @date 2016年7月22日 下午2:22:47
 * @version V1.0
 */
public final class DateConvertUtil {
    /**
     * 格式化日期。
     * 
     * @param date 需格式化的日期
     * @param pattern 格式化日期的格式
     * @return 日期
     * @throws ParseException
     */
    public static Date getFormatDate(String date, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    /**
     * 日期格式化
     * 
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static String getFormatDate(Date date, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 日期加上相应年数。
     * 
     * @param startDate 日期
     * @param years 年数
     * @return 加上年数后日期
     */
    public static Date addYears(Date startDate, int years) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.plusYears(years);
        return dateTime.toDate();
    }

    /**
     * 日期减去相应年数。
     * 
     * @param startDate 日期
     * @param years 年数
     * @return 减去天数后日期
     */
    public static Date minusYears(Date startDate, int years) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.minusYears(years);
        return dateTime.toDate();
    }

    /**
     * 日期加上相应月数。
     * 
     * @param startDate 日期
     * @param months 月数
     * @return 加上月数后日期
     */
    public static Date addMonths(Date startDate, int months) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.plusMonths(months);
        return dateTime.toDate();
    }

    /**
     * 日期减去相应月数。
     * 
     * @param startDate 日期
     * @param months 月数
     * @return 减去月数后日期
     */
    public static Date minusMonths(Date startDate, int months) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.minusMonths(months);
        return dateTime.toDate();
    }

    /**
     * 日期加上相应周数。
     * 
     * @param startDate 日期
     * @param weeks 周数
     * @return 加上周数后日期
     */
    public static Date addWeeks(Date startDate, int weeks) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.plusWeeks(weeks);
        return dateTime.toDate();
    }

    /**
     * 日期减去相应周数。
     * 
     * @param startDate 日期
     * @param weeks 周数
     * @return 减去周数后日期
     */
    public static Date minusWeeks(Date startDate, int weeks) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.minusWeeks(weeks);
        ;
        return dateTime.toDate();
    }

    /**
     * 日期加上相应天数。
     * 
     * @param startDate 日期
     * @param day 天数
     * @return 加上天数后日期
     */
    public static Date addDays(Date startDate, int day) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.plusDays(day);
        return dateTime.toDate();
    }

    /**
     * 日期减去相应天数。
     * 
     * @param startDate 日期
     * @param day 天数
     * @return 减去天数后日期
     */
    public static Date minusDays(Date startDate, int day) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.minusDays(day);
        return dateTime.toDate();
    }

    /**
     * 日期加上相应小时。
     * 
     * @param startDate 日期
     * @param day 小时
     * @return 加上小时后日期
     */
    public static Date addHours(Date startDate, int hours) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.plusHours(hours);
        return dateTime.toDate();
    }

    /**
     * 日期减去相应小时。
     * 
     * @param startDate 日期
     * @param day 小时
     * @return 减去小时后日期
     */
    public static Date minusHours(Date startDate, int hours) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.minusHours(hours);
        return dateTime.toDate();
    }

    /**
     * 日期加上相应分钟。
     * 
     * @param startDate 日期
     * @param day 分钟
     * @return 加上分钟后日期
     */
    public static Date addMinutes(Date startDate, int minutes) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.plusMinutes(minutes);
        return dateTime.toDate();
    }

    /**
     * 日期减去相应分钟。
     * 
     * @param startDate 日期
     * @param day 分钟
     * @return 减去分钟后日期
     */
    public static Date minusMinutes(Date startDate, int minutes) {
        DateTime dateTime = new DateTime(startDate);
        dateTime = dateTime.minusMinutes(minutes);
        return dateTime.toDate();
    }

    /**
     * 获取俩个月份中间的集合
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static String[] getMothsArr(String startDate, String endDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        DateTime s = new DateTime(startDate);
        DateTime e = new DateTime(endDate);
        Period pp = new Period(s, e, PeriodType.months());
        int m = pp.getMonths();
        String[] dateArr = new String[m + 1];
        dateArr[0] = startDate;
        for (int i = 1; i <= m; i++) {
            Date ndate = DateConvertUtil.addMonths(format.parse(startDate), i);
            dateArr[i] = format.format(ndate);
        }
        return dateArr;
    }

    /**
     * 前一日期小于后一日期
     * 
     * @param compareDate1
     * @param compareDate2
     * @return
     */
    public static boolean compareDate(String compareDate1, String compareDate2) {
        DateTime dateTime1 = new DateTime(compareDate1);
        DateTime dateTime2 = new DateTime(compareDate2);
        if (dateTime1.isBefore(dateTime2.getMillis())) {
            return true;
        }
        return false;
    }

    /**
     * 获取第几个季度
     * 
     * @param month
     * @return int
     */
    public static int getSeason(int month) {
        int season = 1;
        if (month >= 1 && month <= 3) {
            season = 1;
        }
        if (month >= 4 && month <= 6) {
            season = 2;
        }
        if (month >= 7 && month <= 9) {
            season = 3;
        }
        if (month >= 10 && month <= 12) {
            season = 4;
        }
        return season;
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int bdateMinSmdateToDays(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个字符串的日期格式之间相差的天数
     * 
     * @param smdate 较小的时间
     * @param bdate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int bdateMinSmdateToDays(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * @throws ParseException
     */
    public static String[] getNowAndOldYearArrs(String starDate, String endDate) throws ParseException {
        String[] dateArrs = getMothsArr(starDate, endDate);
        String[] dateArr = new String[2 * dateArrs.length];
        int j = 0;
        for (int i = 0; i < dateArrs.length; i++) {
            if (i == 0) {
                dateArr[i] = dateArrs[i];
                Date nowDate = minusMonths(formateDate(dateArrs[i], "yyyy-MM"), 12);
                dateArr[i + 1] = formateString(nowDate, "yyyy-MM");
                j = 1;
            } else {
                dateArr[j + 1] = dateArrs[i];
                Date nowDate = minusMonths(formateDate(dateArrs[i], "yyyy-MM"), 12);
                dateArr[j + 2] = formateString(nowDate, "yyyy-MM");
                j = j + 2;
            }
        }
        return dateArr;
    }

    /**
     * 得到某个季度的头一个月
     * 
     * @param month
     * @return int
     */
    public static int getSeasonFirstMoth(int season) {
        int array[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        return array[season - 1][0];
    }

    /**
     * 转换为整数
     */
    public static String formatInt(Double d) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#");// 转换成整型
        return df.format(d);
    }

    public static String formatDecimalWithFraction(Double d) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("##0.0");
        return df.format(d);
    }

    public static String formatDecimalWithTwoFraction(Double d) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("##0.00");
        return df.format(d);
    }

    public static Date formateDate(String date, String formate) throws ParseException {
        SimpleDateFormat fml = new SimpleDateFormat(formate);
        return fml.parse(date);
    }

    public static String formateString(Date date, String formate) throws ParseException {
        SimpleDateFormat fml = new SimpleDateFormat(formate);
        return fml.format(date);
    }

    /**
     * 生成随机时间
     * 
     * @param beginDate 指定范围开始时间
     * @param endDate 指定范围结束时间
     * @return
     */
    public static Date randomDate(String beginDate, String endDate, String DataType) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DataType);
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    public static void main(String[] args) throws ParseException {
        /*
         * BigDecimal b = new BigDecimal(1.23); b.toString(); DateConvertUtil.formatInt(1.23E+3); DateConvertUtil.formatDecimalWithFraction(1456.23E+3);
         * DateConvertUtil.formatDecimalWithTwoFraction(1.23E+3);
         */
        /*
         * String s = "2013-05"; String e = "2014-08"; SimpleDateFormat fml = new SimpleDateFormat("yyyy-MM"); System.out.println(fml.format(minusMonths(fml.parse(s), 12)));
         * System.out.println(fml.format(minusMonths(fml.parse(e), 12))); String[] dateArr = DateConvertUtil.getMothsArr(s, e); for (int i = 0; i < dateArr.length; i++) {
         * System.out.println(dateArr[i]); }
         */
        Date date = DateConvertUtil.formateDate("2015-03-31 14:00:00", "yyyy-MM-dd HH:mm:ss");// 当前时间
        Date date1 = DateConvertUtil.formateDate("2016-03-31 14:00:01", "yyyy-MM-dd HH:mm:ss");// 当前时间
        // Date date1 = DateConvertUtil.formateDate("2016-03-31 13:59:59", "yyyy-MM-dd HH:mm:ss");//当前时间
        // Date date2 = addYears(date1, 2);//截止时间
        // long date2 = new Date().getTime();//截止时间
        System.out.println(bdateMinSmdateToDays(date, date1));
        /*
         * System.out.println("date="+date); System.out.println("date="+date.getTime()); System.out.println("date1="+date1.getTime()); System.out.println("date2="+date2);
         * if(date.getTime() > date1.getTime()){ System.out.println("1"); }else{ System.out.println("2"); } if(date.before(new Date())){ System.out.println("yyy"); }
         */
        /*
         * if(date.equals(date2)){ System.out.println("1"); } if(date.before(date2)){ System.out.println("2"); } if(!date.after(date2)){ System.out.println("3"); }
         */
    }
}
