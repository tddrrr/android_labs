package com.example.projdam;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class}, version = 1, exportSchema = false)
public abstract class CountryDB extends RoomDatabase {
    abstract CountryDAO countryDAO();
}
