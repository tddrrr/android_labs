package com.example.practice_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdaugaCitat extends AppCompatActivity {
    private EditText et_autor;
    private EditText et_text;
    private EditText et_nrAprecieri;
    private Spinner spn_categorie;
    private ArrayAdapter<String> spinnerAdapter;
    private Button btnSend;
    private Intent intent;
    private Citat citat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_citat);
        intent = getIntent();
        initComponents();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salveazaCitat();
            }
        });
    }

    private void initComponents() {
        et_autor = findViewById(R.id.et_autor);
        et_text = findViewById(R.id.et_text);
        et_nrAprecieri = findViewById(R.id.et_nrAprecieri);
        spn_categorie = findViewById(R.id.spn_categorie);
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Categorie));
        spn_categorie.setAdapter(spinnerAdapter);
        btnSend = findViewById(R.id.btn_send);
    }

    private boolean validate() {
        if (et_autor.getText() == null || et_autor.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Id invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_text.getText() == null || et_autor.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Id invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (et_nrAprecieri.getText() == null || et_autor.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Id invalid",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void salveazaCitat() {
        if (validate()) {
            String autor = et_autor.getText().toString();
            String text = et_text.getText().toString();
            String categorie = spn_categorie.getSelectedItem().toString();
            int nrAprecieri = Integer.parseInt(et_nrAprecieri.getText().toString());
            citat = new Citat(autor, text, nrAprecieri, categorie);
            intent.putExtra("CitatAdaugat", citat);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}