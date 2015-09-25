package com.biscarri.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.biscarri.cantina.R;
import com.biscarri.fragment.TableFragment;
import com.biscarri.fragment.TableListFragment;
import com.biscarri.model.Table;
import com.biscarri.model.Tables;

public class MainActivity extends AppCompatActivity implements TableListFragment.TableListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.table_list) != null) {
            if (fm.findFragmentById(R.id.table_list) == null) {
                fm.beginTransaction()
                        .add(R.id.table_list, TableListFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        }

        if (findViewById(R.id.table_detail) != null) {
            TableFragment tableFragment = new TableFragment();
            Table table = Tables.getInstance().getTables().get(0);
            tableFragment.setTable(table);
            if (fm.findFragmentById(R.id.table_detail) == null) {
                fm.beginTransaction()
                        .add(R.id.table_detail, tableFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }
    }



    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();

        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public void onTableSelected(Table table, int index) {
        if (findViewById(R.id.table_detail) != null) {
            TableFragment tableFragment = TableFragment.newInstance();
            tableFragment.setTable(table);
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.table_detail, tableFragment)
                    .commit();
        }
        else {
            TableFragment tableFragment = TableFragment.newInstance();
            tableFragment.setTable(table);
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.table_list, tableFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
