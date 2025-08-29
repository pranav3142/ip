package botchat.util;

import java.time.format.DateTimeFormatter;

public class DateTime {
    public static final DateTimeFormatter INPUT_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter INPUT_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter INPUT_TIME = DateTimeFormatter.ofPattern("HHmm");
    public static final DateTimeFormatter OUTPUT_DATE = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter OUTPUT_DATETIME = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    public static final DateTimeFormatter OUTPUT_TIME = DateTimeFormatter.ofPattern("h:mma");

    private DateTime() {

    }


}
