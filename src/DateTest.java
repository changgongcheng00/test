import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class DateTest {
    public static void main(String[] args) {
        Date d1 = new Date();
        Date d2 = new Date(System.currentTimeMillis()+100);
        System.out.println(d2);
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.before(d2));

        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        System.out.println(c.get(YEAR));
        System.out.println(c.get(MONTH));
        System.out.println(c.get(DATE));
        c.set(2003,10,23,12,32,33);//2003-11-23 12:32:33
        c.add(YEAR,-1);//上一级会发生进位，如加月份，进年
        c.roll(MONTH,-8 );//上一级不会发生进位
        c.setLenient(false);//关闭容错性

        //jdk8 time
        //clock
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
        System.out.println(System.currentTimeMillis());
        //Duration
        Duration d = Duration.ofSeconds(6000);
        System.out.println(d.toMinutes());//6000s相当于？分
        System.out.println(d.toHours());
        System.out.println(d.toDays());
        Clock c2 = Clock.offset(clock,d);
        System.out.println(c2.instant());//当前时间加6000s为
        //Instant
        Instant instant = Instant.now();
        System.out.println(instant);
        Instant instant2 = instant.plusSeconds(6000);
        System.out.println(instant2);
        //根据字符串解析instant对象
        Instant instant3 = instant.parse("2014-02-23T10:12:35.342Z");
        System.out.println(instant3);
        Instant instant4 = instant3.plus(Duration.ofHours(5).plusMinutes(4));
        System.out.println(instant4);
        Instant instant5 = instant4.minus(Duration.ofDays(5));
        System.out.println(instant5);
        //LocalDate
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        localDate = LocalDate.ofYearDay(2014,146);//获取2014年的第146天
        System.out.println(localDate);
        localDate = LocalDate.of(2014, Month.MAY,21);//2014-5-21
        System.out.println(localDate);
        //LocalTime
        LocalTime localTime = LocalTime.now();
        localTime = LocalTime.of(22,33);//设置22:33
        System.out.println(localTime);
        localTime = LocalTime.ofSecondOfDay(5503);//一天中的5503s
        System.out.println(localTime);
        //LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime future = localDateTime.plusHours(25).plusMinutes(3);//现在的时间加上25小时3分钟
        //Year,YearMonth,MonthDay
        Year year = Year.now();
        System.out.println(year);
        year = year.plusYears(5);
        System.out.println(year);
        YearMonth ym = year.atMonth(10);//根据指定月份获取YearMonth
        System.out.println(ym);
        ym = ym.plusYears(5).minusMonths(3);//加5年减3个月
        System.out.println(ym);
        MonthDay md = MonthDay.now();
        System.out.println(md);
        MonthDay md2 = md.with(Month.MAY).withDayOfMonth(23);//5-23
        System.out.println(md2);

        //DateTimeFormatter
        DateTimeFormatter[] formatters = new DateTimeFormatter[]{
          //直接使用常亮创建格式器
          DateTimeFormatter.ISO_LOCAL_DATE,DateTimeFormatter.ISO_LOCAL_TIME,DateTimeFormatter.ISO_LOCAL_DATE_TIME,
          //使用本地化的不同风格来创建格式器
          DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,FormatStyle.MEDIUM),
          DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG),
          //根据模式字符串来创建格式器
          DateTimeFormatter.ofPattern("Gyyyy%%MMM%%DD HH:mm:ss")
        };
        LocalDateTime date1 = LocalDateTime.now();
        //依次用不同的格式器格式化
        for (int i = 0; i <formatters.length ; i++) {
            System.out.println(date1.format(formatters[i]));
            System.out.println(formatters[i].format(date1));
        }
    }
}
