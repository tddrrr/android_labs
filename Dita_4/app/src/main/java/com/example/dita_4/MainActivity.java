package com.example.dita_4;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    //am dat click pe home
                    Log.i("MainActivityDrawer", "a fost apasat butonul home");
                    Toast.makeText(getApplicationContext(), getString(R.string.show_option, item.getTitle()), Toast.LENGTH_LONG).show();
                }
                if (item.getItemId() == R.id.nav_contact) {
                    //am dat click pe contact
                    Toast.makeText(getApplicationContext(), getString(R.string.show_option, item.getTitle()), Toast.LENGTH_LONG).show();
                }
                drawerLayout.closeDrawer(GravityCompat.START); //sa inchidem meniul automat
                return true;
            }
        });
    }

    private void configNavigation() {
        //initializare toolbar - bara de actiune
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //gaseste click-ul pe toolbar
        //initializare drawer layout - panou meniu lateral
        drawerLayout = findViewById(R.id.drawer_layout);
        //legare meniul lateral cu bara actiune + eveniment d edeschidere si creare instanta
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle( //asta e burgerul
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle); // actiunea/ataseaza eveniment
        actionBarDrawerToggle.syncState(); //se realizeaza intoarcerea aia a burgerului
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.old_main_menu, menu); //inflate e ca find view by id, dar pt fisiere mari
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.new_expense_menu) {
            Log.i("MainActivityOldMenu", "Optiunea new expense a fost apasata");
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}