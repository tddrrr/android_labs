package com.example.seminar6;

public class Country {
    private String countryName;
    private String capital;
    private boolean isSelected;

    public Country(String countryName, String capital, boolean isSelected) {
        this.countryName = countryName;
        this.capital = capital;
        this.isSelected= isSelected;
    }

    public String getCountryName() {
        return countryName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryName='" + countryName + '\'' +
                ", capital='" + capital + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
