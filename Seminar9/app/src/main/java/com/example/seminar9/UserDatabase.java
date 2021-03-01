package com.example.seminar9;

import android.content.Context;

import androidx.room.Room;

public class UserDatabase { //clasa intermediara pt accesul la baza de date
    private static UserDatabase instance;
    private UserDB userDB;

    private UserDatabase(Context context) {
        userDB = Room.databaseBuilder(context, UserDB.class, "table_users")
                .allowMainThreadQueries()
                .build();
    }

    public static UserDatabase getInstance(Context context) {//context il folosim sa apelam constructorul
        if (instance == null) {
            instance = new UserDatabase(context);
        }
        return instance;
    }

    public UserDAO userDAO() {
        return userDB.userDAO(); //avem acces la DAO deci la toate metodele de acces la db
    }

//    public void insertUser(User user, callback) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                userDAO().insertUser(user);
//                callback.succes();
//            }
//        })
//    }
}
