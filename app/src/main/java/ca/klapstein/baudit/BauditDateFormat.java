package ca.klapstein.baudit;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Helper class that provides the default {@code SimpleDateFormat} for Baudit.
 */
public class BauditDateFormat {
    static public SimpleDateFormat getFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
    }
}
