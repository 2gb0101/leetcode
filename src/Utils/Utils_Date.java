package Utils;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils_Date {
    @Test
    public void test() {
        Date d = new Date();
        System.out.println(d);
//        大致地看了下，发现Date类现在没什么可以用的方法了，像什么getYear()这样系列的已经不能用了！
//        能用的就只有after()、before()、clone()。我怀疑就是让我们用字典函数，也可以将date格式化。
//        其中还有一个getTime方法使用来获取自1970年1月1日00:00时间以来的豪秒数！
//        不如用GregorianCalendar来的自在
        Calendar calendar = new GregorianCalendar();
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //获取时间，参数是获取的时间类型，这个可以通过直接使用下面这句查看自己想要什么，然后当参数就行了！
        System.out.println(calendar.toString());//toString方法
        System.out.println(calendar.getTime());//直接返回一个Date对象，打印出来和第一个Date结果一样
        System.out.println(calendar.after(new GregorianCalendar(2018,9,30,0,0,0)));//判断时间是否在参数时间后
        System.out.println(calendar.before(new GregorianCalendar(2018,9,30,0,0,0)));//判断时间是否在参数时间前
        Calendar calendar1 = (Calendar) calendar.clone();//克隆方法
        System.out.println(calendar1);//一样
        System.out.println(calendar.compareTo(new GregorianCalendar(2018,9,30,0,0,0)));//比较，这个返回-1，即早-1晚1
        System.out.println(calendar.equals(new GregorianCalendar(2018,9,10)));//如果只设置了日期的话时间默认是网上0点整（参考下面这句），即时间不相等返回false
        System.out.println(new GregorianCalendar(2018,9,10));
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));//这个是根据时间获得最大值，如本月日期最大为30（现在9月）
        System.out.println(calendar.getMaximum(Calendar.DAY_OF_MONTH));//而月的日期的最大能是31了（即在大月的时候！）
//      获得最小值的示例略
//      getDisplayName和getDisplayNames暂时好像没什么用！不展示了
        System.out.println(calendar.getFirstDayOfWeek());//有些地方以星期一为第一天，有些则以周日，这个是获取当地第一天的星期数！
        System.out.println(calendar.getGreatestMinimum(Calendar.DAY_OF_YEAR));//网上解释说最高最小值，凌乱了！

        /*
         * 鬼知道有什么用，文档说：
         * Gets the Gregorian Calendar change date.  This is the point when the
         * switch from Julian dates to Gregorian dates occurred. Default is
         * October 15, 1582 (Gregorian). Previous to this, dates will be in the Julian
         * calendar.
         * @return the Gregorian cutover date for this <code>GregorianCalendar</code> object.
         */
        System.out.println(((GregorianCalendar) calendar).getGregorianChange());
        System.out.println(calendar.getLeastMaximum(Calendar.DAY_OF_MONTH));//该又是返回最大值中的最小值吧！没用！
        System.out.println(calendar.getTimeInMillis());//返回毫秒数啰！
        System.out.println(calendar.getTimeZone());//显示时区是上海sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,useDaylight=false,transitions=19,lastRule=null]
        System.out.println(((GregorianCalendar) calendar).isLeapYear(2008));//判断某一年是否为闰年
        //下面这个是获取宽容度值对于宽容度，网上是这样解释的：
        //如果设置了宽容度即lenient为true，则在设置日期时可以接受比其生成日期的范围还要大的值，例如我们设置日期为1月32号，在get的时候计算日期就会将其计算成1月1号
        //而如果为false的话就不能。（注意：这里设置的时候只是将值赋予了相关参数，所以设置的时候值不符合规范不会报错，而是在get计算日期的时候报错！
        System.out.println(calendar.isLenient());
    }

    @Test
    public void test1() {
        Date d = new Date();
        Date d1 = new Date(120, 11, 28);
        System.out.println(dateDiff(d,d1));
    }

    public static final String dateFormat = "yyyy-MM-dd";
    public static final String dateNumFormat = "yyyyMMdd";

    public static String getDateNumString(Date date){
        return getDateString(date, dateNumFormat);
    }

    public static String getDateString(Date date, String dateNumFormat) {
        return new SimpleDateFormat(dateNumFormat).format(date);
    }

    /**
     * 按默认格式解析日期字符串
     * 注意，DateFormat未实现同步，多线程同时调用该方法时，不能共用一个DateFormat对象
     * 所以这里采用的是每次都new一个新的DateForamt对象，以解决多线程问题
     */
    public static java.util.Date toUtilDate(String s){
        DateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        return dateFormatter.parse(s, new ParsePosition(0));
    }

    public static java.sql.Date toSqlDate(String s){
        java.util.Date ud = toUtilDate(s);
        return new java.sql.Date(ud.getTime());
    }

    public static java.sql.Timestamp toTimestampDate(String s){
        java.util.Date ud = toUtilDate(s);
        return new java.sql.Timestamp(ud.getTime());
    }


    /**
     * 返回两个日期相差toDate-fromDate的天数
     * 当toDate比fromDate时间早时，返回负数
     */
    public static int dateDiff(java.util.Date fromDate, java.util.Date toDate){
        int year = 0;
        int month = 0;
        int day = 0;

        GregorianCalendar fromCal = new GregorianCalendar();
        GregorianCalendar toCal = new GregorianCalendar();

        toCal.setTime(toDate);
        year = toCal.get(Calendar.YEAR);
        month = toCal.get(Calendar.MONTH);
        day = toCal.get(Calendar.DAY_OF_MONTH);
        toCal = new GregorianCalendar(year, month, day);

        fromCal.setTime(fromDate);
        year = fromCal.get(Calendar.YEAR);
        month = fromCal.get(Calendar.MONTH);
        day = fromCal.get(Calendar.DAY_OF_MONTH);
        fromCal = new GregorianCalendar(year, month, day);

        return (int)round(
                (toCal.getTimeInMillis() - fromCal.getTimeInMillis())
                / (1000 * 3600 * 24), 0
        );
    }

    /**
     * 返回两个日期相差endDate-startDate的天数
     * 当endDate比startDate时间早时，返回负数
     */
    public static int monthDiff(java.util.Date startDate, java.util.Date endDate){
        Calendar calStart = Calendar.getInstance();
        Calendar calEnd = Calendar.getInstance();

        calStart.setTime(startDate);
        calEnd.setTime(endDate);
        int endYear = calStart.get(Calendar.YEAR), endMonth = calStart.get(Calendar.MONTH);
        int startYear = calEnd.get(Calendar.YEAR), startMonth = calEnd.get(Calendar.MONTH);
        return startMonth + (startYear - endYear) * 12 - endMonth;
    }

    /**
     * 返回两个日期相差endDate-startDate的天数
     * 当endDate比startDate时间早时，返回负数
     */
    public static int yearDiff(java.util.Date startDate, java.util.Date endDate){
        int yearDiff = 0;
        GregorianCalendar cld = new GregorianCalendar();

        cld.setTime(endDate);
        int year = cld.get(Calendar.YEAR);
        cld.setTime(startDate);
        year -= cld.get(Calendar.YEAR);
        yearDiff = year;

        return yearDiff;
    }

    public static final double round(double v, int lDecs){
        return round(new BigDecimal(v), lDecs, false).doubleValue();
    }

    /**
     * @param lDecs 保留多少位小数
     * @param bTrunc 是否截断
     * @return
     */
    private static BigDecimal round(BigDecimal num, int lDecs, boolean bTrunc) {
        if(lDecs < 0){
            return num;
        }

        BigDecimal one = new BigDecimal("1");
        //DOWN：直接去掉多余的位数
        //HALF_UP:四舍五入
        return num.divide(one, lDecs, bTrunc ? RoundingMode.DOWN : RoundingMode.HALF_UP);
    }
}
