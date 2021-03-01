package eu.ase.ro.sesiunea1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import eu.ase.ro.sesiunea1.R;

public class DraftFragment extends Fragment {

    public static final String NAME = "name";

    private TextView tvMessage;

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