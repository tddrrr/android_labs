package com.example.seminar_d.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seminar_d.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DraftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DraftFragment extends Fragment {

    private static final String NAME = "name";
    TextView tvMessage;
    public DraftFragment() {
        // Required empty public constructor
    }

    public static DraftFragment newInstance(String name) {
        DraftFragment fragment = new DraftFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_draft, container, false);
        tvMessage = view.findViewById(R.id.draft_message);
        if (getArguments() != null) {
            String name = getArguments().getString(NAME);
            tvMessage.setText(name);
        }
        return view;
    }
}