package com.chanxi.utils;

import java.util.Calendar;
import java.util.Date;

public final class Utils {

    public static long getEndTimeOfDay(long timeInmillis) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(timeInmillis);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);

        return instance.getTimeInMillis();
    }

    public static long getEndTimeOfDay(Date date) {
        return getEndTimeOfDay(date.getTime());
    }
}
