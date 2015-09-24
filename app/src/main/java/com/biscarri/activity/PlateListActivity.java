package com.biscarri.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.biscarri.cantina.R;
import com.biscarri.fragment.PlateFragment;

/**
 * Created by joanbiscarri on 22/09/15.
 */
public class PlateListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_list);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_plate_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_plate_list, PlateFragment.newInstance())
                    .commit();
        }

    }
}
