package ca.klapstein.baudit;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Helper class that provides the default {@code SimpleDateFormat} for Baudit.
 *
 * Dates are formatted to a {@code "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"} format.
 */
public class BauditDateFormat {
    static public SimpleDateFormat getBauditDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
    }
}
