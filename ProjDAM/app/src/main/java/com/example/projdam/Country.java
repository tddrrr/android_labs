package com.example.projdam;

import androidx.room.Entity;

@Entity(tableName = "countries")

public class Country {
    private int id;
    private String name;
    private String capital;
    private String language;
    private String currency;
    private String president;

    public Country(String name, String capital, String language, String currency, String president) {
        this.name = name;
        this.capital = capital;
        this.language = language;
        this.currency = currency;
        this.president = president;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }
}
