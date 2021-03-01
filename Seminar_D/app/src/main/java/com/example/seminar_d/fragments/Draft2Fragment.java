package com.example.seminar_d.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seminar_d.R;


public class Draft2Fragment extends Fragment {

    public Draft2Fragment() {
        // Required empty public constructor
    }

    public static Draft2Fragment newInstance() {
        Draft2Fragment fragment = new Draft2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_draft2, container, false);
    }
}