package com.example.seminar7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvTest;
    private ListView lvCountries;
    private CountryAdapter countryAdapter;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tvTest=findViewById(R.id.tvTest);
        lvCountries = findViewById(R.id.lv_countries);
        button = findViewById(R.id.btnAdd);
        editText = findViewById(R.id.edit_text);

        CountriesDownloadManager.getInstance().getCountryData(new ICountryResponse() {
            @Override
            public void onSuccess(final ArrayList<Country> result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        View.OnClickListener onClickListener = new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                result.add((Country) editText.getText());
                                editText.setText("");
                                countryAdapter.notifyDataSetChanged();
                            }
                        };
                        button.setOnClickListener(onClickListener);
                        countryAdapter = new CountryAdapter(result, MainActivity.this);
                        lvCountries.setAdapter(countryAdapter);
                        //tvTest.setText(result.toString());
                        for (Country country : result) {
                            Log.v("remote", country.toString());
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
    }
}