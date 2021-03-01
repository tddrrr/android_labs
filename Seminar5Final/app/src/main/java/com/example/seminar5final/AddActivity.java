package com.example.seminar5final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddActivity extends AppCompatActivity {

    private EditText etNume;
    private EditText etPrenume;
    private EditText etMail;
    private Spinner spFacultate;
    private RadioGroup rgFormaInv;
    private Button btnSubmit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
        setupListeners();

        if (getIntent() != null) {
            if (getIntent().hasExtra("test")) {
                String text = getIntent().getStringExtra("test");
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initViews() {
        etNume = findViewById(R.id.et_nume);
        etPrenume=findViewById(R.id.et_prenume);
        etMail = findViewById(R.id.et_email);
        spFacultate= findViewById(R.id.sp_facultate);
        rgFormaInv = findViewById(R.id.rg_forma);
        btnSubmit2=findViewById(R.id.btn_submit_add);
    }

    private void setupListeners() {
        btnSubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateContent()) {
                    Student student = new Student();
                    student.setNume(etNume.getText().toString());
                    student.setPrenume(etPrenume.getText().toString());
                    student.setEmail(etMail.getText().toString());
                    student.setFacultate(spFacultate.getSelectedItem().toString());

                    int selectedId=rgFormaInv.getCheckedRadioButtonId();
                    RadioButton rbSelected=findViewById(selectedId);
                    student.setFormInv(rbSelected.getText().toString());

                    Intent intent=new Intent();
                    intent.putExtra("student", student);
                    setResult(MainActivity.ADD_REQUEST_CODE, intent);
                    finish();
                    Log.v("add_activity",student.toString());
                }
            }
        });
    }

    private boolean validateContent() {
        if (etNume.getText().toString().isEmpty()) {
            Toast.makeText(this,"Numele trb completat", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etPrenume.getText().toString().isEmpty()) {
            Toast.makeText(this,"Prenumele trb completat", Toast.LENGTH_LONG).show();
            return false;
        }
        String email=etMail.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(this,"Email trb completat", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this,"Email format invalid", Toast.LENGTH_LONG).show();
            return false;
        }

        if (rgFormaInv.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Forma inv", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
}
