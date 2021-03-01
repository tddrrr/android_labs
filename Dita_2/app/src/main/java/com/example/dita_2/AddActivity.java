package com.example.dita_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class AddActivity extends AppCompatActivity {
    private Spinner spnFaculties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //initializare
        spnFaculties = findViewById(R.id.spinner_add_faculty);
        //preluare valori statice pentru spinner
        //initializare adapter cu valori pentru spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.add_faculty_values,
                                                                            android.R.layout.simple_spinner_dropdown_item);
        //setare adapter la nivel spinner
        spnFaculties.setAdapter(adapter);

    }
}