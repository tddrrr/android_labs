package eu.ase.ro.seminar3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import eu.ase.ro.seminar3.util.Student;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_STUDENT_REQUEST_CODE = 210;

    private FloatingActionButton fabAdd;
    private FloatingActionButton fabTax;
    private ListView lvStudents;
    private List<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializare componente vizuale
        initComponents();
        //adaugare adapter pe listView
        addLvStudentsAdapter();
        //adaugare eveniment click
        fabAdd.setOnClickListener(addRequestStudentClickEvent());
        fabTax.setOnClickListener(addOpenTaxClickEvent());
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //verificam ca requestCode-ul este cel pe care l-am trimis ca parametru pe startActivityForResult
        //verificam ca resultCode-ul este cel setat in AddActivity pe setResult
        //verificam ca intent-ul nu a venit null, deoarece l-am populat cu studentul introdus
        if (requestCode == ADD_STUDENT_REQUEST_CODE
                && resultCode == RESULT_OK && data != null) {
            //preluare student din intent
            Student student = (Student) data
                    .getSerializableExtra(AddActivity.STUDENT_KEY);
            if (student != null) {
                //afisarea studentului pe care l-am primit din AddActivity
                Toast.makeText(getApplicationContext(),
                        getString(R.string.student_added_message, student.toString()),
                        Toast.LENGTH_LONG).show();
                students.add(student);
                //preluare adapter setat pe ListView
                ArrayAdapter adapter = (ArrayAdapter) lvStudents.getAdapter();
                //notificare adapter pentru redesenarea valorilor pe ecran, deoarece am modificat
                //lista de studenti si ar trebui sa avem unul nou pe ecran
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void initComponents() {
        //legare FloatingActionButton din main_activity
        fabAdd = findViewById(R.id.fab_main_add);
        fabTax = findViewById(R.id.fab_main_tax);
        //legare ListView din main_activity
        lvStudents = findViewById(R.id.lv_main_students);
    }

    private View.OnClickListener addRequestStudentClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deschidere activitate
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE);
            }
        };
    }

    private View.OnClickListener addOpenTaxClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deschidere activitate
                Intent intent = new Intent(getApplicationContext(), TaxActivity.class);
                startActivity(intent);
            }
        };
    }

    private void addLvStudentsAdapter() {
        //initializare ArrayAdapter de tip Student pentru adaugare pe ListView
        //la fel ca in cazul spinner-ului si ListView-ul are nevoie de un adapter pentru
        //afisarea informatiilor pe ecran
        //in cazul ListView-ului datele nu mai sunt statice,
        // ci toti studentii sunt adaugati dinamic de la tastatura
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                students);
        lvStudents.setAdapter(adapter);
    }
}