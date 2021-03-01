package eu.ase.ro.sesiunea1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.main_grid_view);

        Intent intent = new Intent(getApplicationContext(), AddFragmentActivity.class);
        startActivity(intent);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.grid_view_values, android.R.layout.simple_list_item_1);

        gridView.setAdapter(adapter);
    }
}