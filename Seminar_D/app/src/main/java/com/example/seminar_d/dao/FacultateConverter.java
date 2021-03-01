package com.example.seminar_d.dao;

import androidx.room.TypeConverter;

public class FacultateConverter {

    @TypeConverter
    public static String fromFacultate(Facultate facultate){
        if(facultate == null) {
            return null;
        }
        return facultate.name(); //"CSIE"
    }

    public static Facultate fromString(String facultateString){
        if (facultateString == null || facultateString.isEmpty()) {
            return null;
        }
        return Facultate.valueOf(facultateString); //facultate.name().equals(f);
    }
}
