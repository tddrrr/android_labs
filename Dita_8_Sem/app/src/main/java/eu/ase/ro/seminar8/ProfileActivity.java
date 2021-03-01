package eu.ase.ro.seminar8;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    public static final String SHARED_PREF_FILE_NAME = "profileSharedPref";
    public static final String NAME = "name";
    public static final String GENDER_RB_ID = "gender_rb_id";
    private TextInputEditText tietName;
    private RadioGroup rgGender;
    private Button btnSave;

    //obiect utilizat pentru a reprezenta un fisier de preferinte incarcat in memorie
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initComponents();
    }

    private void initComponents() {
        tietName = findViewById(R.id.tiet_profile_name);
        rgGender = findViewById(R.id.rg_profile_gender);
        btnSave = findViewById(R.id.btn_profile_save);

        //deschidere fisier, daca numele specificat nu exista atunci acesta o sa fie creat pe loc.
        preferences = getSharedPreferences(SHARED_PREF_FILE_NAME, MODE_PRIVATE);
        btnSave.setOnClickListener(saveProfileDetailsEventListener());
        getProfileDetailsFromSharedPreference();
    }

    private void getProfileDetailsFromSharedPreference() {
        //citire informatii din fisierul de preferinte in functie de cheie.
        //cheia este aceeasi ca la operatia put
        String name = preferences.getString(NAME, "");
        int genderRbId = preferences.getInt(GENDER_RB_ID, R.id.rb_profile_gender_male);
        //incarcare informatii in componente vizuale
        tietName.setText(name);
        rgGender.check(genderRbId);
    }

    private View.OnClickListener saveProfileDetailsEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO seminar 8
                saveProfileDetailsToSharedPreference();
                finish();
            }
        };
    }

    private void saveProfileDetailsToSharedPreference() {
        //preluare informatii din componentele vizuale
        String name = tietName.getText() != null ? tietName.getText().toString() : "";
        int genderRbId = rgGender.getCheckedRadioButtonId();
        //definire editor pentru a putea scrie in fisierul de preferinte
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, name);
        editor.putInt(GENDER_RB_ID, genderRbId);
        editor.apply(); // lipsa acestui apel duce la pirderea modificarilor efectuate prin metodele put
    }
}