package com.biscarri.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.biscarri.activity.PlateListActivity;
import com.biscarri.cantina.R;
import com.biscarri.model.OrderElement;
import com.biscarri.model.Table;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class TableFragment extends Fragment {
    public static final String EXTRA_TABLE_INDEX =
            "com.biscarri.activity.TableFragment.EXTRA_TABLE_INDEX";

    private Table mTable;

    //TODO pass selected table to controller

    public static TableFragment newInstance() {
        return new TableFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // int initialTableIndex = getActivity().getIntent().getIntExtra(EXTRA_TABLE_INDEX, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_table, container, false);
        if (mTable != null) {
            TextView tableName = (TextView) root.findViewById(R.id.table_name);
            tableName.setText(mTable.getTableName());

            ListView list = (ListView) root.findViewById(R.id.table_plate_list);
            final ArrayAdapter<OrderElement> adapter = new ArrayAdapter<OrderElement>(
                    getActivity(),
                    android.R.layout.simple_list_item_1, // Layout de cada fila
                    mTable.getOrder().getOrderElementList());
            list.setAdapter(adapter);

            // Obtenemos la referencia al FAB para decirle qué pasa si lo pulsan
            FloatingActionButton addOrderElementButton = (FloatingActionButton) root.findViewById(R.id.add_order_element_button);
            if (addOrderElementButton != null)
                addOrderElementButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), PlateListActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
        }
        return root;
    }

    public void setTable(Table table) {
        mTable = table;
    }

}
