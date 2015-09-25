package com.biscarri.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.biscarri.activity.PlateListActivity;
import com.biscarri.cantina.R;
import com.biscarri.model.OrderElement;
import com.biscarri.model.Plate;
import com.biscarri.model.Plates;
import com.biscarri.model.Table;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class TableFragment extends Fragment {
    public static final String EXTRA_TABLE_INDEX =
            "com.biscarri.activity.TableFragment.EXTRA_TABLE_INDEX";

    private Table mTable;
    private ElementOrderBroadcastReceiver mBroadcastReceiver;

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
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    tryingToRemoveOrderElement(position, adapter);
                    return true;
                }
            });


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

            mBroadcastReceiver = new ElementOrderBroadcastReceiver(adapter);
            // Me suscribo a notificaciones broadcast
            getActivity().registerReceiver(
                    mBroadcastReceiver,
                    new IntentFilter(OrderElement.ADD_NEW_ELEMENT_ORDER));

        }

        return root;
    }

    public void tryingToRemoveOrderElement(final int position, final ArrayAdapter adapter) {
        AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getActivity());
        confirmDialog.setMessage("Deseas eliminar el plato del pedido?");
        confirmDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTable.getOrder().removeOrderElementAtPosition(position);
                adapter.notifyDataSetChanged();
            }
        });
        confirmDialog.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        confirmDialog.show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mBroadcastReceiver);
        mBroadcastReceiver = null;
    }

    public void setTable(Table table) {
        mTable = table;
    }


    private class ElementOrderBroadcastReceiver extends BroadcastReceiver {
        private ArrayAdapter mAdapter;

        // Necesito el adapter al que voy a avisar de que hay nuevos datos
        public ElementOrderBroadcastReceiver(ArrayAdapter adapter) {
            super();
            mAdapter = adapter;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            //Add data
            int plateId = intent.getIntExtra("PLATE_ID", 0);
            String comment = intent.getStringExtra("COMMENT");
            Plate plate = Plates.getInstance().getPlateWithId(plateId);

            if (plate != null) {
                OrderElement orderElement = new OrderElement(mTable, plate, comment);
                mTable.getOrder().addOrderElement(orderElement);
                // Hay nuevos cambios, aviso al adaptador para que vuelva a recargarse
                mAdapter.notifyDataSetChanged();
            }

        }
    }


}
