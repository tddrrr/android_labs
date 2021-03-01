package com.example.seminar6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadCountryManager {

        private static DownloadCountryManager instance;

        private DownloadCountryManager() {}

        public static DownloadCountryManager getInstance() {
            if (instance == null) {
                instance = new DownloadCountryManager();
            }
            return instance;
        }

        public void getRemoteData(final ICountryResult listener) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("https://api.mocki.io/v1/8ce33c93");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        InputStream stream = connection.getInputStream();
                        InputStreamReader streamReader = new InputStreamReader(stream);
                        BufferedReader reader = new BufferedReader(streamReader);
                        StringBuilder result = new StringBuilder();
                        String line = "";
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }
                        reader.close();
                        streamReader.close();
                        stream.close();
                        listener.onSuccess(result.toString());
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

}
