package eu.ase.ro.seminar5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import eu.ase.ro.seminar5.fragments.AboutFragment;
import eu.ase.ro.seminar5.fragments.HomeFragment;
import eu.ase.ro.seminar5.util.Expense;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_EXPENSE_REQUEST_CODE = 210;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Fragment currentFragment;
    private ArrayList<Expense> expenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        initComponents();
        openDefaultFragment(savedInstanceState);
    }

    private void initComponents() {
        navigationView = findViewById(R.id.main_nav_view);
        //atasare eveniment de click pe optiunile din meniul lateral
        navigationView.setNavigationItemSelectedListener(addNavigationMenuItemSelectedEvent());
    }

    private NavigationView.OnNavigationItemSelectedListener addNavigationMenuItemSelectedEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.main_nav_home) {
                    //marcam currentFragment ca fiind home
                    currentFragment = HomeFragment.newInstance(expenses);
                } else if (item.getItemId() == R.id.nav_contact) {
                    //marcam currentFragment ca fiind contact
                    currentFragment = new AboutFragment();
                }
                Toast.makeText(getApplicationContext(),
                        getString(R.string.show_option, item.getTitle()),
                        Toast.LENGTH_SHORT)
                        .show();
                //incarcam pe ecran fragmentul corespunzator optiunii selectate
                openFragment();
                //inchidem meniul lateral
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    private void configNavigation() {
        //initializare toolbar - bara de actiune
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initializare drawer layout - panou meniu lateral
        drawerLayout = findViewById(R.id.drawer_layout);
        //legare meniu lateral cu bara actiune
        // + eveniment de deschidere
        //creare instanta utilitara
        ActionBarDrawerToggle actionBar =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        //atasare eveniment
        drawerLayout.addDrawerListener(actionBar);
        //sincronizare actionBartoggle
        actionBar.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //atasare meniu clasic de activitatea principala
        getMenuInflater().inflate(R.menu.old_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.old_main_menu_add) {
            //deschiderea activitatii New Expense cu scopul de a incarca in aplicatie o noua cheltuiala.
            Intent intent = new Intent(getApplicationContext(), NewExpenseActivity.class);
            startActivityForResult(intent, NEW_EXPENSE_REQUEST_CODE);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //validarea pe campurile requestCode si resultCode pentru a identifica operatia de adaugare cheltuiala
        if (requestCode == NEW_EXPENSE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Expense expense = (Expense) data.getParcelableExtra(NewExpenseActivity.NEW_EXPENSE_KEY);
            if (expense != null) {
                Toast.makeText(getApplicationContext(), R.string.new_expense_added,
                        Toast.LENGTH_SHORT).show();
                expenses.add(expense);
                if (currentFragment instanceof HomeFragment) {
                    ((HomeFragment) currentFragment).notifyInternalAdapter();
                }
            }
        }
    }

    /******************* FRAGMENTE ******************/
    private void openDefaultFragment(Bundle savedInstanceState) {
        //savedInstanceState este null daca s-a deschis prima data activitate. La back nu este null
        if (savedInstanceState == null) {
            //consideram HomeFragment ca fragment default, prin urmare facem o instanta si trimitem lista de cheltuieli
            currentFragment = HomeFragment.newInstance(expenses);
            //incarcam fragmentul pe ecranul dispozitivului mobil
            openFragment();
            //marcam in meniul lateral optiunea Home ca fiind selectat (fundal gri)
            navigationView.setCheckedItem(R.id.main_nav_home);
        }
    }

    private void openFragment() {
        //se preia managerul de la nivelul appCompatActivity pentru a putea adauga un nou fragment
        //in interiorul unui FrameLayout
        getSupportFragmentManager()
                .beginTransaction()//incepe tranzactia pentru adaugarea fragmentului
                .replace(R.id.main_frame_container, currentFragment)//se inlocuieste FrameLayout din content_main.xml cu fisierul xml a fragmentul initializat
                .commit();//se confirma schimbarea
    }
}