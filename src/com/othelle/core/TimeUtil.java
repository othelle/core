package com.othelle.core;

import com.othelle.core.time.TimeUnit;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * User: v.vlasov
 * Date: 12/17/11
 */
public class TimeUtil {
    public static long getDayWithoutTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));

        Calendar res = Calendar.getInstance();
        res.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        res.set(Calendar.MILLISECOND, 0);
        return res.getTimeInMillis();
    }

    public static long getDayDelta(long time) {
        return getDayDelta(time, System.currentTimeMillis());
    }

    public static long getDayDelta(long time, long current) {
        long daysOld = TimeUnit.MILLISECONDS.toDays(time + TimeZone.getDefault().getOffset(time));
        long daysNow = TimeUnit.MILLISECONDS.toDays(current + TimeZone.getDefault().getOffset(current));
        return daysNow - daysOld;
    }

    public static boolean isTheSameWeek(long time, long current) {
        Calendar instance = Calendar.getInstance();
        Calendar another = Calendar.getInstance();

        instance.setTimeInMillis(time);
        another.setTimeInMillis(current);

        return
                instance.get(Calendar.YEAR) == another.get(Calendar.YEAR) &&
                        instance.get(Calendar.WEEK_OF_YEAR) == another.get(Calendar.WEEK_OF_YEAR);
    }
}
