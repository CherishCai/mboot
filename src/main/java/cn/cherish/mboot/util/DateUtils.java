package cn.cherish.mboot.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 严格的日期转换setLenient(false);
 * setLenient
 * public void setLenient(boolean lenient)指定日期/时间解析是否不严格。
 * 进行不严格解析时，解析程序可以使用启发式的方法来解释与此对象的格式不精确匹配的输入。
 * 进行严格解析时，输入必须匹配此对象的格式。
 * 参数：
 * lenient - 为 true 时，解析过程是不严格的
 * 不会自动将错误日期转换为正确的日期
 * 例如:19450000,使用原DateUtil会转换为19441130
 * @author : SIMON
 */
public class DateUtils {
    public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";
    public static final String YM = "yyyyMM";
    public static final String NORMAL_DATE_FORMAT = "yyyy-MM-dd";
    public static final String NORMAL_DATE_FORMAT_NEW = "yyyy-mm-dd hh24:mi:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_ALL = "yyyyMMddHHmmssS";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String HM = "HH:mm";

    public static Long strDateToNum(String param) throws Exception {
        if (param == null)
            return null;
        String[] strings = null;
        String str = "";
        if (param.contains("-")) {
            strings = param.split("-");
            for (int i = 0; i < strings.length; ++i)
                str = str + strings[i];
            return Long.parseLong(str);
        }
        return Long.parseLong(param);
    }

    public static Long strDateToNum1(String param) throws Exception {
        if (param == null)
            return null;
        String[] strings = null;
        String str = "";
        if (param.contains("-")) {
            strings = param.split("-");
            for (int i = 0; i < strings.length; ++i)
                if (strings[i].length() == 1)
                    str = str + "0" + strings[i];
                else
                    str = str + strings[i];
            return Long.parseLong(str);
        }
        return Long.parseLong(param);
    }

    public static String numDateToStr(Long paramLong) {
        if (paramLong == null)
            return null;
        String str = null;
        str = paramLong.toString().substring(0, 4) + "-"
                + paramLong.toString().substring(4, 6) + "-"
                + paramLong.toString().substring(6, 8);
        return str;
    }

    public static Date stringToDate(String paramStr, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        try {
            return sdf.parse(paramStr);
        } catch (ParseException e) {
            throw new Exception("解析日期字符串时出错！", e);
        }
    }

    public static String dateToString(Date paramDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);
        return sdf.format(paramDate);
    }

    public static Date compactStringToDate(String paramString) throws Exception {
        return stringToDate(paramString, "yyyyMMdd");
    }

    public static String dateToCompactString(Date paramDate) {
        return dateToString(paramDate, "yyyyMMdd");
    }

    public static String dateToNormalString(Date paramDate) {
        return dateToString(paramDate, "yyyy-MM-dd");
    }

    public static String compactStringDateToNormal(String paramString) throws Exception {
        return dateToNormalString(compactStringToDate(paramString));
    }

    public static int getDaysBetween(Date paramDate1, Date paramDate2) throws Exception {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(paramDate1);
        c2.setTime(paramDate2);
        if (c1.after(c2))
            throw new Exception("起始日期小于终止日期!");
        int i = c2.get(6) - c1.get(6);
        int j = c2.get(1);
        if (c1.get(1) != j) {
            c1 = (Calendar) c1.clone();
            do {
                i += c1.getActualMaximum(6);
                c1.add(1, 1);
            } while (c1.get(1) != j);
        }
        return i;
    }

    public static Date addDays(int differDate) {
        return addDays(null, differDate);
    }

    public static Date addDays(Date paramDate, int differDate) {
        Calendar c = Calendar.getInstance();
        if (paramDate != null) {
            c.setTime(paramDate);
        }
        int i = c.get(6);
        c.set(6, i + differDate);
        return c.getTime();
    }

    public static Date addDays(String dateStr, String pattern, int differDate) throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date localDate = stringToDate(dateStr, pattern);
        calendar.setTime(localDate);
        int i = calendar.get(6);
        calendar.set(6, i + differDate);
        return calendar.getTime();
    }

    public static java.sql.Date getSqlDate(Date paramDate) {
        return new java.sql.Date(paramDate.getTime());
    }

    public static String formatDate(Date paramDate) {
        if (paramDate == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        return sdf.format(paramDate);
    }

    public static String formatDateTime(Date paramDate) {
        if (paramDate == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        return sdf.format(paramDate);
    }

    public static Date parseDate(String paramString) throws Exception {
        if ((paramString == null) || (paramString.trim().equals("")))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            return sdf.parse(paramString);
        } catch (ParseException e) {
            throw new Exception("日期解析出错！", e);
        }
    }

    public static Date parseDateTime(String paramString) throws Exception {
        if ((paramString == null) || (paramString.trim().equals("")))
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        try {
            return sdf.parse(paramString);
        } catch (ParseException e) {
            throw new Exception("时间解析异常！", e);
        }
    }

    public static Integer getYM(String paramString) throws Exception {
        if (paramString == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        Date localDate;
        try {
            localDate = sdf.parse(paramString);
        } catch (ParseException e) {
            throw new Exception("时间解析异常！", e);
        }
        return getYM(localDate);
    }

    public static Integer getYM(Date paramDate) {
        if (paramDate == null)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime(paramDate);
        int i = c.get(1);
        int j = c.get(2) + 1;
        return i * 100 + j;
    }

    public static int addMonths(int paramInt1, int paramInt2) {
        Calendar c = Calendar.getInstance();
        c.set(1, paramInt1 / 100);
        c.set(2, paramInt1 % 100 - 1);
        c.set(5, 1);
        c.add(2, paramInt2);
        return getYM(c.getTime());
    }

    public static Date addMonths(Date paramDate, int paramInt) {
        Calendar c = Calendar.getInstance();
        c.setTime(paramDate);
        c.add(2, paramInt);
        return c.getTime();
    }

    public static int monthsBetween(int paramInt1, int paramInt2) {
        return paramInt2 / 100 * 12 + paramInt2 % 100
                - (paramInt1 / 100 * 12 + paramInt1 % 100);
    }

    public static int monthsBetween(Date paramDate1, Date paramDate2) {
        return monthsBetween(getYM(paramDate1), getYM(paramDate2));
    }

    public static String getChineseDate(Calendar paramCalendar) {
        int i = paramCalendar.get(1);
        int j = paramCalendar.get(2);
        int k = paramCalendar.get(5);
        return String.valueOf(i) + "年" + (j + 1) + "月" + k + "日";
    }

    public static String getChineseWeekday(Calendar paramCalendar) {
        switch (paramCalendar.get(7)) {
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
            case 1:
                return "星期日";
        }
        return "未知";
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(new Date().getTime());
    }
    
    
   /* public static void main(String[] paramArrayOfString) {
        try {  
            System.out.println(formatDate(addMonths(parseDate("2013-01-06"), 12)));  
        } catch (Exception localException) {  
            System.out.println(localException);  
        }  
    }  */
}