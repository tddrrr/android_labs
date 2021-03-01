package com.example.bilet_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class AddCar extends AppCompatActivity {
    EditText et_marca;
    EditText et_nrZile;
    Spinner spinnerModel;
    RadioGroup rg_Carburant;
    DatePicker dp_dataInchiriere;
    ArrayAdapter<String> spinnerAdapter;
    Button btnSend;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        intent = getIntent();
        initComponents();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salveazaCar();
            }
        });
    }

    private void initComponents() {
        et_marca = findViewById(R.id.et_marca);
        et_nrZile = findViewById(R.id.et_nrZile);
        spinnerModel = findViewById(R.id.spinner_model);
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.model));
        spinnerModel.setAdapter(spinnerAdapter);
        rg_Carburant = findViewById(R.id.rg_carburant);
        dp_dataInchiriere = findViewById(R.id.dp_data);
        btnSend = findViewById(R.id.btn_send);
    }

    private boolean validate() {
        if (et_marca.getText() == null || et_marca.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Id invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_nrZile.getText() == null || et_nrZile.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Id invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void salveazaCar() {
        if (validate()) {
            String marca = et_marca.getText().toString();
            String model = spinnerModel.getSelectedItem().toString();
            Integer choice = rg_Carburant.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(choice);
            String carburant = radioButton.getText().toString();
            long dataInchiriere = dp_dataInchiriere.getCalendarView().getDate();
            Integer nrZile = Integer.parseInt(et_nrZile.getText().toString());
            Car car = new Car(marca, model, carburant, dataInchiriere, nrZile);
//            Toast.makeText(getApplicationContext(), car.toString(), Toast.LENGTH_LONG);
            intent.putExtra("CarAdaugat", car);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


}