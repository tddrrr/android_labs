package com.example.practice_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int COD_ADAUGA_REZERVARE = 200;
    private static final int COD_EDITEAZA_REZERVARE = 300;
    private List<Rezervare> rezervari = new ArrayList<>();
    private ListView lvRezervari;
    private RezervariService rezervariService;
    int pozitie;
    private String urlAddress = "https://api.mocki.io/v1/2e46b4de";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rezervariService = new RezervariService();
        lvRezervari = findViewById(R.id.lv_rezervari);
        //adaugare adapter pe ListView
        adaugaLV();
        lvRezervari.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pozitie = position;
                Intent intent = new Intent(getApplicationContext(), AdaugaRezervare.class);
                intent.putExtra("deEditat", (Serializable) rezervari.get(position));
                startActivityForResult(intent, COD_EDITEAZA_REZERVARE);
            }
        });
        lvRezervari.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pozitie = position;
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("Suneti sigur ca doriti stergerea elementului?")
                        .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                rezervari.remove(pozitie);
                                ArrayAdapter adapter =(ArrayAdapter) lvRezervari.getAdapter();
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Nu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Ati renuntat la stergere",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); //maparea/injectia in instanta menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdauga) {
            Intent intent = new Intent(getApplicationContext(), AdaugaRezervare.class);
            startActivityForResult(intent, COD_ADAUGA_REZERVARE);
        }
        if (item.getItemId() == R.id.menuDespre) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.despre))
                    .setMessage("Utilizator - "+ getResources().getString(R.string.Teodora)+ " Data - " + Calendar.getInstance().getTime())
                    .setIcon(R.drawable.ic_android_black_24dp)
                    .create();
            alertDialog.show();
        }
        if (item.getItemId() == R.id.menuPreluare) {
            RezervareJSON rezervareJSON = new RezervareJSON(){
                @Override
                protected void onPostExecute(List<Rezervare> listaRezervari) {
                    super.onPostExecute(listaRezervari);
                    for (Rezervare rezervare: listaRezervari) {
                        rezervari.add(rezervare);
                        ArrayAdapter adapter = (ArrayAdapter)lvRezervari.getAdapter();
                        adapter.notifyDataSetChanged();
                    }
                }
            };
            rezervareJSON.execute();
        }
        if (item.getItemId() == R.id.menuGrafic) {
            Intent intentChart = new Intent(getApplicationContext(), ChartActivity.class);
            ArrayList<Rezervare> rezervariChart = new ArrayList<>();
            for (Rezervare rezervare : rezervari) {
                rezervariChart.add(rezervare);
            }
            intentChart.putParcelableArrayListExtra("rezervariChart", rezervariChart);
            startActivity(intentChart);
        }
        if (item.getItemId() == R.id.menuSalvareBD) {
            adaugareDB();
        }
        if (item.getItemId() == R.id.menuAll) {
            getAllFromDB();
        }
        return super.onOptionsItemSelected(item);
    }

    private void adaugareDB() {
        if (rezervari != null) {
            for (int i=0; i < rezervari.size(); i++) {
                insertIntoDB(rezervari.get(i));
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void insertIntoDB(Rezervare rezervare) {
        new RezervariService.Insert(getApplicationContext()) {
            @Override
            protected void onPostExecute(Rezervare rezervare) {
                if (rezervare != null) {
                    Log.i("Db inserted", rezervare.toString());
                }
            }
        }.execute(rezervare);
    }

    private void getAllFromDB() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                final List<Rezervare> results = rezervariService.getAllRezervari();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (results != null) {
                            rezervari.clear();
                            rezervari.addAll(results);
                            ArrayAdapter adapter = (ArrayAdapter)lvRezervari.getAdapter();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        };
        thread.start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_ADAUGA_REZERVARE && resultCode == RESULT_OK && data != null) {
            Rezervare rezervare = (Rezervare)data.getSerializableExtra("RezervareAdaugata");
            if (rezervare != null) {
                rezervari.add(rezervare);
                ArrayAdapter adapter = (ArrayAdapter)lvRezervari.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == COD_EDITEAZA_REZERVARE && resultCode == RESULT_OK && data != null) {
            Rezervare rezervareEditata = (Rezervare)data.getSerializableExtra("RezervareAdaugata");
            if (rezervareEditata != null) {
                rezervari.get(pozitie).setDataCazare(rezervareEditata.getDataCazare());
                rezervari.get(pozitie).setNumeClient(rezervareEditata.getNumeClient());
                rezervari.get(pozitie).setIdRezervare(rezervareEditata.getIdRezervare());
                rezervari.get(pozitie).setTipCamera(rezervareEditata.getTipCamera());
                rezervari.get(pozitie).setDurataSejur(rezervareEditata.getDurataSejur());
                rezervari.get(pozitie).setSumaPlata(rezervareEditata.getSumaPlata());
                ArrayAdapter adapter = (ArrayAdapter)lvRezervari.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void adaugaLV() {
        ArrayAdapter<Rezervare> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                rezervari
                );
        lvRezervari.setAdapter(adapter);
    }
    //preluare din json cu AsyncTask: httpManager, asyncTask si JSONParser
    class RezervareJSON extends AsyncTask<String, Void, List<Rezervare>> {

        @Override
        protected List<Rezervare> doInBackground(String... strings) {
            List<Rezervare> rezervari = new ArrayList<>();
            try {
                URL url = new URL(urlAddress);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder rezultat = new StringBuilder();
                String linie;
                while ((linie = bufferedReader.readLine()) != null) {//cat timp mai am de citit
                    rezultat.append(linie);
                }
                //json parser
                String objectString = rezultat.toString();
                JSONObject object = new JSONObject(objectString);
                JSONObject rezervariObject = object.getJSONObject("rezervari");
                JSONArray rezervariArray = rezervariObject.getJSONArray("rezervare");
                for (int i=0; i< rezervariArray.length(); i++) {
                    JSONObject rezervareObject = rezervariArray.getJSONObject(i);
                    int idRezervare = rezervareObject.getInt("idRezervare");
                    String numeClient = rezervareObject.getString("numeClient");
                    String tipCamera = rezervareObject.getString("tipCamera");
                    int durataSejur = rezervareObject.getInt("durataSejur");
                    int sumaPlata = rezervareObject.getInt("sumaPlata");
                    String data = rezervareObject.getString("dataCazare");
                    Date dataCazare = null;
                    try {
                        dataCazare = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALY).parse(data);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Rezervare rezervare = new Rezervare(idRezervare, numeClient, tipCamera, durataSejur, sumaPlata, dataCazare);
                    rezervari.add(rezervare);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return rezervari;
        }
    }
}