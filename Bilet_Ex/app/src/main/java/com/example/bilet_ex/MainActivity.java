package com.example.bilet_ex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int COD_ADAUGA_CAR = 200;
    private Button btnAdauga;
    Intent intent;
    private List<Car> cars= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdauga = findViewById(R.id.btn_adauga);
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), AddCar.class);
                startActivityForResult(intent, COD_ADAUGA_CAR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_ADAUGA_CAR && resultCode == RESULT_OK && data != null) {
            Car car = data.getParcelableExtra("CarAdaugat");
            if (car != null) {
                cars.add(car);
            }
        }
    }
}