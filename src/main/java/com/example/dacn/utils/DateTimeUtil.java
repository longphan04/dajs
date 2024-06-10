package com.example.dacn.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Timestamp convertDateToTimeStamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static Date convertTimestampToDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    public static String getSimpleFormatDate(Date date) {
        return simpleDateFormat.format(date);
    }
}
