package com.example.projdam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDatabase.getInstance(this).userDAO().insertUser(new User("Birliba", "Teodora", "teodora.birliba@gmail.com",
                "teodora15"));
        CountryDatabase.getInstance(this).countryDAO().insertCountry(new Country("Romania", "Bucharest",
                "romanian", "ron", "Klaus Iohannis"));
        List<User> users = UserDatabase.getInstance(this).userDAO().getAllUsers();
        List<Country> countries = CountryDatabase.getInstance(this).countryDAO().getAllCountries();
        List<String> capitals = CountryDatabase.getInstance(this).countryDAO().getAllCapitals("Romania");
        Log.v("users_from_db", users.toString());
        Log.v("countries_from_db", countries.toString());
        Log.v("capitals_from_db", capitals.toString());

        writePreferences();
        readPreferences();
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
}