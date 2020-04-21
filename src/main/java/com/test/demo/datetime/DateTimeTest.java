package com.test.demo.datetime;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * DateTimeTest
 *
 * @author WangChao
 * @date 2020/04/20
 */
public class DateTimeTest {

    public static void main(String[] args) {
        //1、如何取得年月日、小时分钟秒？
        //方式一
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH));    // 0 - 11
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));

        //2、Java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue());     // 1 - 12
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());

        //3、如何取得从1970年1月1日0时0分0秒到现在的毫秒数？
        Calendar.getInstance().getTimeInMillis();
        System.currentTimeMillis();
        Clock.systemDefaultZone().millis(); // Java 8

        //4、如何取得某月的最后一天？
        Calendar time = Calendar.getInstance();
        time.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

}