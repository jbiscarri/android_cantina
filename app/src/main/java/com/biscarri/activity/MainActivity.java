package com.biscarri.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.biscarri.cantina.R;
import com.biscarri.fragment.TableFragment;
import com.biscarri.fragment.TableListFragment;
import com.biscarri.model.Table;

public class MainActivity extends AppCompatActivity implements TableListFragment.TableListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment, TableListFragment.newInstance())
                    .commit();
        }


    }

    @Override
    public void onTableSelected(Table table, int index) {
        TableFragment tableFragment = TableFragment.newInstance();
        tableFragment.setTable(table);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment, tableFragment)
                .commit();
    }
}
