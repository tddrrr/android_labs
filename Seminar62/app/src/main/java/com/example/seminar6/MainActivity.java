package com.example.seminar6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvCountries;
    private Button btnRemote;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCountries = findViewById(R.id.lv_countries);
        setupListAdapter();

        btnRemote = findViewById(R.id.btn_remote);
        final DownloadCountryManager ref1= DownloadCountryManager.getInstance();
        final DownloadCountryManager ref2= DownloadCountryManager.getInstance();

        btnRemote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("singleton", ref1.toString());
                Log.v("singleton", ref2.toString());
            }
        });

    }

    private void setupListAdapter() {
        countryAdapter = new CountryAdapter(getDummyList(), this);
        lvCountries.setAdapter(countryAdapter);
        lvCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, countryAdapter.getItem(position).toString(), Toast.LENGTH_LONG);
            }
        });
        lvCountries.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                countryAdapter.removeElement(position);
                countryAdapter.addElements(getDummyList());
                return true;
            }
        });
    }

    private ArrayList<Country> getDummyList() {
        Country c1 = new Country("Romania", "Bucuresti", true);
        Country c2 = new Country("Bulgaria", "Sofia", true);
        Country c3 = new Country("Japonia", "Tokyo", true);
        Country c4 = new Country("Franta", "Paris", true);

        ArrayList<Country> list=new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);

        return list;
    }
}