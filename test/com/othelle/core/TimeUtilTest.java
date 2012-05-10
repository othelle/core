package com.othelle.core;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.othelle.core.TimeUtil.getDayDelta;
import static com.othelle.core.TimeUtil.isTheSameWeek;
import static org.junit.Assert.assertThat;

/**
 * User: v.vlasov
 * Date: 12/17/11
 */

public class TimeUtilTest {
    @Test
    public void testGetDayDelta() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        long format1 = format.parse("2011-12-01 23:22").getTime();
        long format2 = format.parse("2011-12-02 12:22").getTime();
        long dayDelta = getDayDelta(format1, format2);
        assertThat(dayDelta, Matchers.equalTo(1L));

        long dayDelta1 = getDayDelta(format.parse("2011-11-30 23:22").getTime(), format.parse("2011-12-02 12:22").getTime());
        assertThat(dayDelta1, Matchers.equalTo(2L));

        long dayDelta2 = getDayDelta(format.parse("2012-12-01 23:22").getTime(), format.parse("2011-12-02 12:22").getTime());
        assertThat(dayDelta2, Matchers.equalTo(-365L));

        assertThat(getDayDelta(format.parse("2011-12-02 12:22").getTime(), format.parse("2011-11-30 23:22").getTime()),
                Matchers.equalTo(-2L));
    }

    @Test
    public void testSameWeek() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");


        assertThat(isTheSameWeek(format.parse("2011-11-30 23:22").getTime(), format.parse("2011-12-02 12:22").getTime()),
                Matchers.equalTo(true));


        assertThat(isTheSameWeek(format.parse("2011-11-30 23:22").getTime(), format.parse("2011-12-07 12:22").getTime()),
                Matchers.equalTo(false));
    }
}
