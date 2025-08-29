package botchat.util;

import java.time.format.DateTimeFormatter;

/**
 * To format the date into standard formats.
 */
public class DateTime {
    public static final DateTimeFormatter INPUT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter INPUT_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final DateTimeFormatter OUTPUT_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter OUTPUT_DATETIME = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");



}
