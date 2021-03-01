package com.example.seminar3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("lifeCycle", "onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("lifeCycle", "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("lifeCycle", "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("lifeCycle", "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("lifeCycle", "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("lifeCycle", "onDestroy");

    }
}