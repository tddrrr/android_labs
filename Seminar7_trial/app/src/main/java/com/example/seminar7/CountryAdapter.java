package com.example.seminar7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter extends BaseAdapter {

    private ArrayList<Country> countries;
    private Context context;
    private LayoutInflater inflater;

    public CountryAdapter(ArrayList<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return countries.size(); // creeaza atatea elem cat are lista
    }

    @Override
    public Country getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //creeaza view pentru fiecare element in parte
        final View countryView = inflater.inflate(R.layout.item_country, parent, false);
        TextView tvCountryName = countryView.findViewById(R.id.tv_country_name);
        TextView tvCapital = countryView.findViewById(R.id.tv_capital);

        Country country = countries.get(position);
        tvCapital.setText(country.getCapital());
        tvCountryName.setText(country.getCountryName());
        return countryView;
    }

    public void removeElement(int position) {
        countries.remove(position);
        notifyDataSetChanged();
    }
    public void addElement(ArrayList<Country> list) {
        countries.addAll(list);
        notifyDataSetChanged();
    }
}
