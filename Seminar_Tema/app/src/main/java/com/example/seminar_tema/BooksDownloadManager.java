package com.example.seminar_tema;

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

public class BooksDownloadManager {
    private BooksDownloadManager() {}

    private static BooksDownloadManager instance;

    public static BooksDownloadManager getInstance() {
        if (instance == null) {
            instance = new BooksDownloadManager();
        }
        return instance;
    }

    public void getBookData(final IBookResponse listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.mocki.io/v1/ae6b4710");
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

                    ArrayList<Book> books = parseBookData(stringBuilder.toString());
                    listener.onSuccess(books);
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

    private ArrayList<Book> parseBookData(String result) {
        ArrayList<Book> books= new ArrayList<>();
        try {
            JSONObject booksObj= new JSONObject(result);
            JSONArray booksArray = booksObj.getJSONArray("books");
            for (int i=0 ; i<booksArray.length(); i++) {
                JSONObject currentObj = booksArray.getJSONObject(i);
                String bookName = currentObj.getString("name");
                String author = currentObj.getString("author");
                Book book = new Book(bookName, author);
                books.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;
    }
}
