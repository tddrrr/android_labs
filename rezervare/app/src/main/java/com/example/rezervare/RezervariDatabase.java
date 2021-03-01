package com.example.rezervare;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters({DateConverter.class})
public abstract class RezervariDatabase extends RoomDatabase {
    public abstract RezervariDAO getRezervariDAO();
}
