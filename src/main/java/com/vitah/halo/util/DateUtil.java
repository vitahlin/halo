package com.vitah.halo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author vitah
 */
public class DateUtil {

    /**
     * 获取多少天后的日期
     *
     * @param days
     * @return
     */
    public static Date getDateAfterDays(Integer days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);

        return calendar.getTime();
    }
}
