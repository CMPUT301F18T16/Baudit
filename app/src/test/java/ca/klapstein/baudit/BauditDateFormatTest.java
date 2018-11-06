package ca.klapstein.baudit;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BauditDateFormatTest {

    @Test
    public void getBauditDateFormat() throws ParseException {
        SimpleDateFormat dateFormat = ca.klapstein.baudit.BauditDateFormat.getBauditDateFormat();
        assertNotNull(dateFormat);

        // see if we can format a Date into a timestamp
        Date date = new Date();
        String timestamp = dateFormat.format(date);
        assertNotNull(timestamp);

        // ensure we can go from a timestamp to a Date
        assertEquals(date, dateFormat.parse(timestamp));
    }
}