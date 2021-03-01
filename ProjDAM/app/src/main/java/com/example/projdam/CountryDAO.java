package com.example.projdam;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CountryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(Country country);
    @Delete
    void deleteCountry(Country country);
    @Update
    void updateCountry(Country country);
    @Query("SELECT * FROM countries")
    List<Country> getAllCountries();
    @Query("SELECT capital FROM countries WHERE name =:name")
    List<String> getAllCapitals(String name);
}

