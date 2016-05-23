package ru.bigcheese.jsalon.core.util;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by BigCheese on 17.05.16.
 */
public class DateUtilsTest {

    @Test
    public void testAddTime() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Calendar calendar = Calendar.getInstance();

        calendar.set(2016, 4, 17, 0, 0, 0);
        Integer time = 1074;
        DateUtils.addMinutes(calendar, time);
        String result = df.format(calendar.getTime());
        Assert.assertEquals("17.05.2016 17:54", result);

        calendar.set(2016, 4, 17, 0, 0, 0);
        time = 1440;
        DateUtils.addMinutes(calendar, time);
        result = df.format(calendar.getTime());
        Assert.assertEquals("18.05.2016 00:00", result);
    }
}
