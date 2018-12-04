package com.company.augustwokshops;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {


    private DateUtils() {

    }

    public static int getDayOfMonth(Date date) {
        return localDate(date).getDayOfMonth();
    }

    public static LocalDate localDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
