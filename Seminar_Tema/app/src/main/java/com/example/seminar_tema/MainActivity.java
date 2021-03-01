package com.example.seminar_tema;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private BookAdapter bookAdapter;
private ListView lvBooks;
public static int ADD_REQUEST_CODE = 100;
private Button btnSubmit;
private Button btnAlert;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvBooks = findViewById(R.id.lv_books);
        BooksDownloadManager.getInstance().getBookData(new IBookResponse() {
            @Override
            public void onSuccess(final ArrayList<Book> result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bookAdapter = new BookAdapter(result, MainActivity.this);
                        lvBooks.setAdapter(bookAdapter);
                        for (Book book : result) {
                            Log.v("remote", book.toString());
                        }
                    }
                });
            }

            @Override
            public void onFailure(final Throwable error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("Adauga", "Adauga");
                startActivityForResult(intent, ADD_REQUEST_CODE);
            }
        });
        btnAlert = findViewById(R.id.btn_alert);
        builder = new AlertDialog.Builder(this);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aici e to do doar ca n am mai avut timp
                builder.setMessage("Nu am reusit sa termin partea asta");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_REQUEST_CODE) {
            if (data != null) {
                if (data.hasExtra("book")) {
                    Book book = data.getParcelableExtra("book");
                    Log.v("main_activity", book.toString());
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}