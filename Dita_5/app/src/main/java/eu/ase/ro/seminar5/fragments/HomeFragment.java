package eu.ase.ro.seminar5.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import eu.ase.ro.seminar5.R;
import eu.ase.ro.seminar5.util.Expense;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static String EXPENSES_KEY = "expenses_key";

    private ListView lvExpenses;
    private List<Expense> expenses = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(ArrayList<Expense> expenses) {
        HomeFragment fragment = new HomeFragment();
        //Bundle este o clasa asemanatoare cu intentul, doar ca nu poate deschide activitati.
        //Este utilizata pentru transmiterea de informatii intre activitati/fragmente
        //in cazul curente adaugam lista de cheltuieli pentru a o afisa in ListView-ul din fragment Home
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(HomeFragment.EXPENSES_KEY, expenses);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        lvExpenses = view.findViewById(R.id.lv_home_expenses);
        //preiau lista de jucatori
        if (getArguments() != null) {
            expenses = getArguments().getParcelableArrayList(EXPENSES_KEY);
            Log.i("HomeFragment", expenses.toString());
        }
        //creare adapter pentru ListView
        if (getContext() != null) {
            //vezi seminar 3 pentru creare si adaugare adapter ListView
            ArrayAdapter<Expense> adapter = new ArrayAdapter<>(getContext().getApplicationContext(),
                    android.R.layout.simple_list_item_1, expenses);
            lvExpenses.setAdapter(adapter);
        }
    }

    //scopul metodei este de a anunta ListView-ul de la nivelul fragmentului atunci cand se adauga
    //o noua cheltuiala in lista de la nivelul activitatii MainActivity.
    //Aceasta metoda trebuie apelata doar in cazul in care currentFragment din MainActivity este HomeFragment
    public void notifyInternalAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvExpenses.getAdapter();
        adapter.notifyDataSetChanged();
    }
}