package com.example.seminar7;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CountriesDownloadManager {

    private static CountriesDownloadManager instance;

    private CountriesDownloadManager(){}

    public static CountriesDownloadManager getInstance() {
        if (instance == null) {
            instance = new CountriesDownloadManager();

        }
        return instance;
    }

    public void getCountryData(final ICountryResponse listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.mocki.io/v1/2b2aa311");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream stream = connection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(stream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    reader.close();
                    stream.close();
                    Log.v("remote", stringBuilder.toString());

                    ArrayList<Country> countries = parseCountryData(stringBuilder.toString());
                    listener.onSuccess(countries);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFailure(e);
                }
            }
        }).start();
    }

    private ArrayList<Country> parseCountryData(String result) {
        ArrayList<Country> countries= new ArrayList<>();
        try {
            JSONObject countriesObj= new JSONObject(result);
            JSONArray countriesArray = countriesObj.getJSONArray("countries");
            for (int i=0 ; i<countriesArray.length(); i++) {
                JSONObject currentObj = countriesArray.getJSONObject(i);
                String countryName = currentObj.getString("name");
                String capital = currentObj.getString("capital");
                Country country = new Country(countryName, capital);
                countries.add(country);
            }
        } catch (JSONException e) {
                e.printStackTrace();
        }

        return countries;
    }
}
