package com.example.seminar_d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //o preluare dintr-un calendar view
    CalendarView calendarView;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        long timeInMills = calendarView.getDate();
//        Date date = new Date(timeInMills);
//
//        TextView showDate = null;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
//        showDate.setText(simpleDateFormat.format(date));

          gridView = findViewById(R.id.main_grid_view); //cand facem build la aplicatie se creeaza R-ul

            Intent intent = new Intent(getApplicationContext(), AddFragmentActivity.class);
            startActivity(intent);

    }
}