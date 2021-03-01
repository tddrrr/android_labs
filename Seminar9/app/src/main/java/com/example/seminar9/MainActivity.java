package com.example.seminar9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Teodora"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Sofia"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Teodora2"));
//        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Teodora3"));

//        List<User> users = UserDatabase.getInstance(this).userDAO().getAllUsers();
//        Log.v("users_from_db", users.toString());
        writePreferences();
        readPreferences();
        writeToFirebaseDatabase();
    }

    private void writePreferences() {
        SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "Nume");
        editor.putInt("intValue", 22);
        editor.putInt("varsta", 45);

        editor.commit();
    }
    private void readPreferences() {
        SharedPreferences preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        Log.v("read_string", preferences.getString("username", "N/A"));
        Log.v("read_int", preferences.getInt("varsta", 0)+"");
        Log.v("read_string", preferences.getString("dgs", "Not found"));

    }
    private void writeToFirebaseDatabase() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://seminar-android-b59a8-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        myRef.setValue("Another text test");
    }
}