package eu.ase.ro.sesiunea1.dao;

import androidx.room.TypeConverter;

public class FacultateConverter {

    @TypeConverter
    public static String fromFacultate(Facultate facultate) {
        if (facultate == null) {
            return null;
        }
        return facultate.name(); //"CSIE";
    }

    @TypeConverter
    public static Facultate fromString(String facultateString) {
        if (facultateString == null || facultateString.isEmpty()) {
            return null;
        }
        return Facultate.valueOf(facultateString); // facultate.name().equals(facultateString);
    }
}
