package eu.ase.ro.seminar5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import eu.ase.ro.seminar5.util.DateConverter;
import eu.ase.ro.seminar5.util.Expense;

public class NewExpenseActivity extends AppCompatActivity {
    public static final String NEW_EXPENSE_KEY = "new_expense_key";

    private TextInputEditText tietDate;
    private TextInputEditText tietAmount;
    private TextInputEditText tietDescription;
    private Spinner spnCategory;
    private Button btnSave;

    private final DateConverter dateConverter = new DateConverter();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        initComponets();
        //preluare intent cu care s-a deschis formularul de introducere de date
        intent = getIntent();
    }

    private void initComponets() {
        tietDate = findViewById(R.id.tiet_new_expense_date);
        tietAmount = findViewById(R.id.tiet_new_expense_amount);
        tietDescription = findViewById(R.id.tiet_new_expense_description);
        spnCategory = findViewById(R.id.spn_new_expense_category);
        btnSave = findViewById(R.id.btn_new_expense_save);
        addCategoryAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Expense expense = createExpenseFromView();
                    intent.putExtra(NEW_EXPENSE_KEY, expense);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private boolean validate() {
        //validare pentru campul date
        if (tietDate.getText() == null || dateConverter.fromString(tietDate.getText().toString()) == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.invalid_date_field_error,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        //validare pentru campul suma
        if (tietAmount.getText() == null || tietAmount.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(),
                    R.string.invalid_amount_field_error,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        //spinnerul Category nu are nevoie de validari deoarece acesta are tot timpul prima valoare selectata
        //campul descriere este optional in contextul aplicatiei prin urmare nu avem nevoie de validare
        return true;
    }

    //construiere obiect Expense cu informatiile populate in componentele vizuale
    private Expense createExpenseFromView() {
        Date date = dateConverter.fromString(tietDate.getText().toString());
        Double amount = Double.parseDouble(tietAmount.getText().toString());
        String description = tietDescription.getText().toString();
        String category = spnCategory.getSelectedItem().toString();
        return new Expense(date, category, amount, description);
    }

    private void addCategoryAdapter() {
        //Adaugare adapter cu valori predefinite din strings.xml pentru spinner (vezi Seminar 2)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.new_expense_categories_values,
                android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapter);
    }
}