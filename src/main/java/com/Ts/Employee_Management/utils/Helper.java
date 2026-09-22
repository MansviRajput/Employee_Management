package com.Ts.Employee_Management.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Helper {

    public static final String DATE_FORMATE = "dd-MM-yyyy";
    public static final String DATE_TIME_FORMATE = "dd-MM-yyyy HH:mm:ss:SSS";

    public static String generateId(){
        return UUID.randomUUID().toString();
    }

    public static String formatDate(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATE);
        return localDateTime.format(formatter);
    }

    public static String formatDate(LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATE);
        return localDate.format(formatter);

    }

    public static String formatBigDecimal(BigDecimal bigDecimal){
        return bigDecimal.toString();
    }
}
