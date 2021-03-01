package com.example.seminar_d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seminar_d.fragments.Draft2Fragment;
import com.example.seminar_d.fragments.DraftFragment;

public class AddFragmentActivity extends AppCompatActivity {
    private Button btnChangeFragment;
    private Fragment selectedFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fragment);
        btnChangeFragment = findViewById(R.id.change_fragment);
        btnChangeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFragment == null || selectedFragment instanceof DraftFragment) {
                    selectedFragment = Draft2Fragment.newInstance();
                } else {
                    selectedFragment = DraftFragment.newInstance("test");
                }
                addFragment(selectedFragment);
            }
        });

    }

    private void addFragment(DraftFragment draftFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, draftFragment)
                .commit();
    }
}