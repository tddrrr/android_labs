package com.example.practice_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdaugaRezervare extends AppCompatActivity {

    private static final String DATE_FORMAT = "dd-MM-yyyy";
    EditText et_idRezervare;
    EditText et_numeClient;
    Spinner spinnerTipCamera;
    EditText et_durataSejur;
    EditText et_durataCazare;
    EditText et_sumaPlata;
    Button btnSend;
    ArrayAdapter<String> spinnerAdapter;
    Intent intent;
    Rezervare rezervare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_rezervare);
        initComponents();
        intent = getIntent();
        if (intent.hasExtra("deEditat")) {
            rezervare = (Rezervare) intent.getSerializableExtra("deEditat");
            preluareComponente();
        }
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salveazaRezervare();
            }
        });

    }

    private void preluareComponente() {
        et_numeClient.setText(rezervare.getNumeClient());
        et_idRezervare.setText(String.valueOf(rezervare.getIdRezervare()));
        et_numeClient.setText(String.valueOf(rezervare.getSumaPlata()));
        et_durataSejur.setText(String.valueOf(rezervare.getDurataSejur()));
        et_sumaPlata.setText(String.valueOf(rezervare.getSumaPlata()));
        spinnerTipCamera.setSelection(spinnerAdapter.getPosition(rezervare.getTipCamera()));
        et_durataCazare.setText(new SimpleDateFormat(DATE_FORMAT, Locale.US).format(rezervare.getDataCazare()));
    }

    private void initComponents() {
        et_idRezervare = findViewById(R.id.et_IdRezervare);
        et_numeClient = findViewById(R.id.et_numeCurs);
        spinnerTipCamera = findViewById(R.id.spn_tipCamera);
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.tipCamera));
        spinnerTipCamera.setAdapter(spinnerAdapter);
        et_durataCazare = findViewById(R.id.et_dataCazare);
        et_durataSejur = findViewById(R.id.et_durataSejur);
        et_sumaPlata = findViewById(R.id.et_sumaPlata);
        btnSend = findViewById(R.id.btn_send);
    }

    public void salveazaRezervare() {
        if (validate()) {
            int idRezervare = Integer.parseInt(et_idRezervare.getText().toString());
            String numeClient = et_numeClient.getText().toString();
            String tipCamera = spinnerTipCamera.getSelectedItem().toString();
            int durataSejur = Integer.parseInt(et_durataSejur.getText().toString());
            int sumaPlata = Integer.parseInt(et_sumaPlata.getText().toString());
            Date dataCazare = null;
            try {
                dataCazare = new SimpleDateFormat(AdaugaRezervare.DATE_FORMAT, Locale.US).parse(et_durataCazare.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            rezervare = new Rezervare(idRezervare, numeClient, tipCamera, durataSejur, sumaPlata, dataCazare);
            intent.putExtra("RezervareAdaugata", (Parcelable) rezervare);
            setResult(RESULT_OK, intent);
            finish();

        }
    }

    public boolean validate() {
        if(et_idRezervare.getText() == null || et_idRezervare.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Id invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_numeClient.getText() == null || et_numeClient.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Nume client invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_durataSejur.getText() == null || et_durataSejur.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Durata sejur invalida",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_sumaPlata.getText() == null || et_sumaPlata.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Suma plata invalida",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_durataCazare.getText() == null || et_durataCazare.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Data cazare invalida",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}