package com.fabernovel.codingdojo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static final DateUtils ourInstance = new DateUtils();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy", Locale.FRANCE);

    public static String formatDate(Date date) {
        return ourInstance.dateFormat.format(date);
    }

    private DateUtils() {
    }
}
