package com.example.seminar_tema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAuthor;
    private Button btnSubmitAdd;
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
        etName = findViewById(R.id.et_name);
        etAuthor = findViewById(R.id.et_author);
        btnSubmitAdd = findViewById(R.id.btn_submit_add);
    }

    private void setupListeners() {
        btnSubmitAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateContent()) {
                    Book book = new Book();
                    book.setBookName(etName.getText().toString());
                    book.setAuthor(etAuthor.getText().toString());

                    Intent intent = new Intent();
                    intent.putExtra("book", (Parcelable) book);
                    setResult(MainActivity.ADD_REQUEST_CODE, intent);
                    finish();
                    Log.v("add_activity",book.toString());

                }
            }
        });
    }

    private boolean validateContent() {
        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(this,"Add book name", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etAuthor.getText().toString().isEmpty()) {
            Toast.makeText(this,"Add author name", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}