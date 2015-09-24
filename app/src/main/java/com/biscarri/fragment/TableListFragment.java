package com.biscarri.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.biscarri.cantina.R;
import com.biscarri.model.Table;
import com.biscarri.model.Tables;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class TableListFragment extends Fragment{

    private TableListListener mListener;


    public static TableListFragment newInstance() {
        return new TableListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        Tables tables = Tables.getInstance();

        ListView list = (ListView) root.findViewById(R.id.table_list);
        final ArrayAdapter<Table> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1, // Layout de cada fila
                tables.getTables());
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener != null) {
                    mListener.onTableSelected(adapter.getItem(position), position);
                }
            }
        });
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (TableListListener) getActivity();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (TableListListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface TableListListener {
        void onTableSelected(Table table, int index);
    }



}
