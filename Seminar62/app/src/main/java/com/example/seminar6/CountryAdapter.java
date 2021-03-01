package com.example.seminar6;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View countryView = inflater.inflate(R.layout.item_country, parent, false);
        CheckBox cbSelected = countryView.findViewById(R.id.cb_selected);
        TextView tvCountryName = countryView.findViewById(R.id.tv_country_name);
        TextView tvCapital = countryView.findViewById(R.id.tv_capital);

        Country country = countries.get(position);
        tvCapital.setText(country.getCapital());
        tvCountryName.setText(country.getCountryName());

        cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    countryView.setBackgroundResource(R.color.colorAccent);
                } else {
                    countryView.setBackgroundResource(R.color.colorPrimaryDark);
                }
            }
        });
        return countryView;
    }

    public void removeElement(int position) {
        countries.remove(position);
        notifyDataSetChanged();
    }

    public void addElements(ArrayList<Country> list) {
        countries.addAll(list);
        notifyDataSetChanged();
    }
}
