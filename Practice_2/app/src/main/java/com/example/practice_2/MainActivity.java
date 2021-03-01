package com.example.practice_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int COD_ADAUGA_CITAT = 200;
    private List<Citat> citate = new ArrayList<>();
    private ListView lvCitate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCitate = findViewById(R.id.lv_citate);
        adaugaListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdauga) {
            Intent intent = new Intent(getApplicationContext(), AdaugaCitat.class);
            startActivityForResult(intent, COD_ADAUGA_CITAT);
        }
        if (item.getItemId() == R.id.menuSincronizare) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_ADAUGA_CITAT && resultCode == RESULT_OK && data != null) {
            Citat citat = data.getParcelableExtra("CitatAdaugat");
            if (citat != null) {
                citate.add(citat);
                ArrayAdapter adapter = (ArrayAdapter) lvCitate.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void adaugaListView() {
        ArrayAdapter<Citat> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, citate);
        lvCitate.setAdapter(adapter);
    }

}