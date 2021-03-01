package com.example.practice_1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = {Rezervare.class}, exportSchema = false, version = 1)
@TypeConverters({DataConverter.class})
public abstract class DatabaseManager extends RoomDatabase {
    private static final String DATABASE = "RezervareDB";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) { //la al doilea thread verific iar
            synchronized (DatabaseManager.class) { //pt prioritizarea threadurilor; daca 2 fragmente mi-ar face 2 apeluri in acelasi timp, synchronized ar ordona procesarea threadurilor
                if (databaseManager == null) { //daca e nul, initializez;
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, DATABASE) //initializare: context, clasa de initial, nume bd
                            .fallbackToDestructiveMigration() //la fiecare modificare asupra tabelei, daca nu merge migrarea, ne sterge inregistrarile din tabela
                            //                    .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return databaseManager;
    }
    //conexiunea cu DAO
    public abstract RezervariDAO getRezervariDAO();
}
