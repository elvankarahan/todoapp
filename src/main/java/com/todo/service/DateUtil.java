package com.todo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getDate(){
        LocalDateTime date = LocalDateTime.now();
        String _date = date.format(DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy"));
        return _date;
    }
}