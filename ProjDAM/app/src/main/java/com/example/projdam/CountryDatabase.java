package com.example.projdam;

import android.content.Context;

import androidx.room.Room;

public class CountryDatabase {
    private static CountryDatabase instance;
    private CountryDB countryDB;

    private CountryDatabase(Context context) {
        countryDB = Room.databaseBuilder(context, CountryDB.class, "table_countries")
                .allowMainThreadQueries()
                .build();
    }

    public static CountryDatabase getInstance(Context context) {//context il folosim sa apelam constructorul
        if (instance == null) {
            instance = new CountryDatabase(context);
        }
        return instance;
    }

    public CountryDAO countryDAO() {
        return countryDB.countryDAO(); //avem acces la DAO deci la toate metodele de acces la db
    }
}
