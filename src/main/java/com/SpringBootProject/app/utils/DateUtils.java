package com.SpringBootProject.app.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
/*
Clase util para mapear las fechas por problemas de fechas en los userEntity y UserDTO
 */

public class DateUtils {

    /*
    Conversion de LocalDate a Date
     */
    public static Date toDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /*
    Conversion de Date a LocalDate
     */
    public static LocalDate toLocalDate(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
