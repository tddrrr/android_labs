package eu.ase.ro.seminar3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import eu.ase.ro.seminar3.util.DateConverter;
import eu.ase.ro.seminar3.util.Student;
import eu.ase.ro.seminar3.util.StudyType;

import static android.widget.ArrayAdapter.createFromResource;

public class AddActivity extends AppCompatActivity {
    public static final String STUDENT_KEY = "student_key";
    private final DateConverter dateConverter = new DateConverter();

    //declarare variabile java corespunzatoare widget-urilor din add_activity.xml
    private TextInputEditText tietName;
    private TextInputEditText tietAge;
    private TextInputEditText tietEnrollmentDate;
    private RadioGroup rgStudyType;
    private Spinner spnFaculties;
    private Button btnSave;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //apel catre metoda care realizarea legatura dintre variabilele Java
        // si componentele vizuale din add_activity.xml
        initComponents();
        //apel metoda pentru popularea spinner-ului cu elemente
        populateSpnFaculties();
        //atasare eveniment de click pe butonul salveaza
        btnSave.setOnClickListener(addSaveClickEvent());
        //preluarea intentului cu care s-a deschis activitatea
        intent = getIntent();
    }

    private void initComponents() {
        //legare textInputEditText nume
        tietName = findViewById(R.id.tiet_add_name);
        //legare textInputEditText varsta
        tietAge = findViewById(R.id.tiet_add_age);
        //legare textInputEditText ziua inscrierii
        tietEnrollmentDate = findViewById(R.id.tiet_add_enrollment_date);
        //legare radioGroup forma de invatamanat
        rgStudyType = findViewById(R.id.rg_add_study_type);
        //legare spinner facultati
        spnFaculties = findViewById(R.id.spn_add_faculties);
        //legare buton salveaza
        btnSave = findViewById(R.id.btn_add_save);
    }

    private void populateSpnFaculties() {
        //creare adapter care asigura convertirea unei colectii de string-uri
        // la o colectie de view (TextView)
        ArrayAdapter<CharSequence> adapter = createFromResource(getApplicationContext(),
                R.array.add_faculties,
                android.R.layout.simple_spinner_dropdown_item);
        //atasam adapter catre spinner
        spnFaculties.setAdapter(adapter);
    }

    private View.OnClickListener addSaveClickEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validarea campurilor de intrare
                if (validate()) {
                    //construire obiect java cu informatiile din interfata
                    Student student = buildStudentFromWidgets();
                    //punere in intent a studentului pe care dorim sa-l trimitem catre MainActivity
                    intent.putExtra(STUDENT_KEY, student);
                    //trimiterea intent-ului catre MainActivity
                    setResult(RESULT_OK, intent);
                    //inchidere activitate curenta
                    finish();
                }
            }
        };
    }

    private boolean validate() {
        //validare pentru campul name
        if (tietName.getText() == null || tietName.getText().toString().trim().length() < 3) {
            //afisare mesaj temporar cu informatia lipsa.
            //Toast este o clasa din Android care permite afisarea de pop-up-uri temporare pe ecran.
            //Aceste pop-up-uri se inchide singure fara actiune din partea utilizatorului
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_name_error,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        //validare pentru campul age
        if (tietAge.getText() == null || tietAge.getText().toString().trim().length() == 0
                || Integer.parseInt(tietAge.getText().toString().trim()) < 18
                || Integer.parseInt(tietAge.getText().toString().trim()) > 70) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_age_error,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        //validare pentru campul enrollment date
        if (tietEnrollmentDate.getText() == null
                || dateConverter.fromString(tietEnrollmentDate.getText().toString().trim()) == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_enrollment_date_error,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    private Student buildStudentFromWidgets() {
        //extragere nume
        String name = tietName.getText().toString();
        //extragere varsta
        int age = Integer.parseInt(tietAge.getText().toString().trim());
        //extragere ziua inscrierii
        Date enrollmentDate = dateConverter.fromString(tietEnrollmentDate.getText().toString().trim());
        //identificare forma de invatamant in functie de radio buttonul selectat in interfata
        //selectam valoarea default sa fie full time
        StudyType studyType = StudyType.FULL_TIME;
        //daca id-ul butonului selectat nu este distance atunci nu modificam valoarea default,
        //altfel alegem distance
        if (rgStudyType.getCheckedRadioButtonId() == R.id.rb_add_study_type_distance) {
            studyType = StudyType.DISTANCE;
        }
        //preluam ce este selectat in spinner
        String faculty = spnFaculties.getSelectedItem().toString();
        return new Student(name, age, enrollmentDate, studyType, faculty);
    }
}