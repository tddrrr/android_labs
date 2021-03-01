package eu.ase.ro.seminar6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import eu.ase.ro.seminar6.util.BankAccount;
import eu.ase.ro.seminar6.util.BankAccountAdapter;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_BANK_ACCOUNT_REQUEST_CODE = 210;

    //declarare componente vizuale
    private FloatingActionButton fabAddBankAccount;
    private ListView lvBankAccounts;

    private List<BankAccount> bankAccounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        bankAccounts.add(new BankAccount("Dita Alexandru",
                1234567890123456L, 10, 2023, "ING"));
        bankAccounts.add(new BankAccount("Dita Alexandru",
                1234567890123456L, 3, 2022, "BRD"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_BANK_ACCOUNT_REQUEST_CODE
                && resultCode == RESULT_OK && data != null) {
            BankAccount bankAccount = (BankAccount) data
                    .getSerializableExtra(AddBankAccountActivity.BANK_ACCOUNT_KEY);
            if (bankAccount != null) {
                Toast.makeText(getApplicationContext(),
                        R.string.main_new_bank_account_added_message,
                        Toast.LENGTH_SHORT)
                        .show();
                bankAccounts.add(bankAccount);
                notifyAdapter();
            }
        }
    }

    private void initComponents() {
        //initializare varaibile Java de tip componente vizuale cu elementele din xml
        fabAddBankAccount = findViewById(R.id.fab_main_add_bank_account);
        lvBankAccounts = findViewById(R.id.lv_main_bank_accounts);
        addBankAccountAdapter();
        fabAddBankAccount.setOnClickListener(addNewBankAccountEventListener());
    }

    private View.OnClickListener addNewBankAccountEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vezi Seminar 3 pentru transfer de parametri (pasul 1- deschidere formular)
                Intent intent = new Intent(getApplicationContext(), AddBankAccountActivity.class);
                startActivityForResult(intent, ADD_BANK_ACCOUNT_REQUEST_CODE);
            }
        };
    }

    private void addBankAccountAdapter() {
        //adaugare adapter pentru listview
//        ArrayAdapter<BankAccount> adapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_list_item_1, bankAccounts);
        BankAccountAdapter adapter = new BankAccountAdapter(getApplicationContext(),
                R.layout.lv_row_view, bankAccounts, getLayoutInflater());
        lvBankAccounts.setAdapter(adapter);
    }

    private void notifyAdapter() {
        //notificare adapter ca s-a adaugat un nou element in lista
        ArrayAdapter adapter = (ArrayAdapter) lvBankAccounts.getAdapter();
        adapter.notifyDataSetChanged();
    }
}