package com.example.practice_1;

import androidx.room.TypeConverter;

import java.util.Date;

public class DataConverter {
    @TypeConverter
    public static Date fromLongToDate(Long date) {
        return date != null ? new Date(date) : null;
    }

    @TypeConverter
    public static Long fromDateToLong(Date date) {
        return date != null ? date.getTime() : null;
    }
}
