package javaKnowledge.jdk;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * Created by xiongjie on 2018/10/19.
 */
public class DateAPI {

    public static void main(String[] args){
//        ClockTest();
//        TimezonesTest();
//        LocalTimeTest();
//        LocalDateTest();
        LocalDateTimeTest();
    }


    /**
     * jdk1.8
     * 0.Clock类
     */
    public static void ClockTest(){
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        Instant instant = clock.instant(); //瞬时时间
        Date legacyDate = Date.from(instant);   // legacy java.util.Date
        System.out.println(millis+"="+instant+"="+legacyDate);
    }

    /**
     * jdk1.8
     * 1.Timezones类
     */
    public static void TimezonesTest(){
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }


    /**
     * jdk1.8
     * 2.LocalTime类
     */
    public static void LocalTimeTest(){

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);       // 23:59:59
        DateTimeFormatter germanFormatter = DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);
        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);   // 13:37

    }

    /**
     * jdk1.8
     * 3.LocalDate类
     */
    public static void LocalDateTest(){

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println(today+"="+tomorrow+"="+yesterday);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);
        DateTimeFormatter germanFormatter = DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   // 2014-12-24

    }

    /**
     * jdk1.8
     * 4.LocalDateTime类
     */
    public static void LocalDateTimeTest(){
//        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
//        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
//        System.out.println(dayOfWeek);
//
//        Month month = sylvester.getMonth();
//        System.out.println(month);
//
//        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
//        System.out.println(minuteOfDay);    // 1439
//
//        Instant instant = sylvester
//                            .atZone(ZoneId.systemDefault())
//                            .toInstant();
//        Date legacyDate = Date.from(instant);
//        System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd,yyyy - HH:mm");
        LocalDateTime parsed = LocalDateTime.parse("Nov 03,2014 - 07:13", formatter);
        String string = formatter.format(parsed);
        System.out.println(string);     // Nov 03, 2014 - 07:13

    }


}
