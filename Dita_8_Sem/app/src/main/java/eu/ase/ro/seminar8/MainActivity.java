package eu.ase.ro.seminar8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import eu.ase.ro.seminar8.asyncTask.AsyncTaskRunner;
import eu.ase.ro.seminar8.asyncTask.Callback;
import eu.ase.ro.seminar8.network.HttpManager;
import eu.ase.ro.seminar8.network.HttpManagerV2;
import eu.ase.ro.seminar8.util.BankAccount;
import eu.ase.ro.seminar8.util.BankAccountAdapter;
import eu.ase.ro.seminar8.util.BankAccountJsonParser;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_BANK_ACCOUNT_REQUEST_CODE = 210;
    public static final String BANK_ACCOUNTS_URL = "https://jsonkeeper.com/b/DHQ3";
    public static final int PROFILE_REQUEST_CODE = 222;

    //declarare componente vizuale
    private FloatingActionButton fabAddBankAccount;
    private FloatingActionButton fabProfile;
    private ListView lvBankAccounts;
    private TextView tvMessage;

    private List<BankAccount> bankAccounts = new ArrayList<>();
    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
//        getBankAccountsFromHttp();
        getBankAccountsFromHttpV2();
    }

    private void getBankAccountsFromHttpV2() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                final String result = new HttpManagerV2(BANK_ACCOUNTS_URL).process();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        bankAccounts.addAll(BankAccountJsonParser.fromJson(result));
                        notifyAdapter();
                    }
                });
            }
        };
        thread.start();
    }

    private void getBankAccountsFromHttp() {
        //definim un obiect de tip Callable pe care dorim sa-l procesam pe un alt fir de executie (RunnableTask)
        //HttpManager implementeaza aceasta interfata.
        Callable<String> asyncOperation = new HttpManager(BANK_ACCOUNTS_URL);
        //definim Callback-ul, adica zona din activitatea unde dorim sa receptionam rezultatul procesarii paralele
        //realizata de Callable
        Callback<String> mainThreadOperation = receiveBankAccountsFromHttp();
        //Apelam asyncTaskRunner cu operation asincrona si zona de cod din activitate unde dorim sa primim raspunsul
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveBankAccountsFromHttp() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                //apelam parsatorul de json, iar rezultatul obtinut il adaugam in lista de obiecte BankAccount
                //existenta la nivelul activitati
                bankAccounts.addAll(BankAccountJsonParser.fromJson(result));
                //avand in vedere ca lista de obiecte este modificata la linia de mai sus,
                // este necesar sa notificam adapterul de acest lucru astfel incat obiectele noi
                //sa fie incarcate in ListView
                notifyAdapter();
            }
        };
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
        } else if (requestCode == PROFILE_REQUEST_CODE) {
            displayMessage();
        }
    }

    private void initComponents() {
        //initializare varaibile Java de tip componente vizuale cu elementele din xml
        fabAddBankAccount = findViewById(R.id.fab_main_add_bank_account);
        lvBankAccounts = findViewById(R.id.lv_main_bank_accounts);
        fabProfile = findViewById(R.id.fab_main_profile);
        tvMessage = findViewById(R.id.tv_main_message);
        addBankAccountAdapter();
        fabAddBankAccount.setOnClickListener(addNewBankAccountEventListener());
        fabProfile.setOnClickListener(profileEventListener());
        displayMessage();
    }

    private void displayMessage() {
        //deschidere fisier de preferinte in memorie
        SharedPreferences preferences = getSharedPreferences(ProfileActivity.SHARED_PREF_FILE_NAME, MODE_PRIVATE);
        //citire valori din fisiere de preferinte
        String name = preferences.getString(ProfileActivity.NAME, "");
        if (name != null && !name.isEmpty()) {
            tvMessage.setText(getString(R.string.main_welcome_message, name));
        }
    }

    private View.OnClickListener profileEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivityForResult(intent, PROFILE_REQUEST_CODE);
            }
        };
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