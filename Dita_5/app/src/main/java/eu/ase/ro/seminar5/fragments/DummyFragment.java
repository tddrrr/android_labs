package eu.ase.ro.seminar5.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.ase.ro.seminar5.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DummyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DummyFragment extends Fragment {

    public DummyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dummy, container, false);
    }
}