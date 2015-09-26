package com.biscarri.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.biscarri.cantina.R;
import com.biscarri.model.OrderElement;
import com.biscarri.model.Plates;
import com.biscarri.model.Table;

/**
 * Created by joanbiscarri on 27/09/15.
 */
public class BillActivity extends Activity {
    public static final String ARG_TABLE_BILL = "TABLE_BILL";
    private Table mTable;
    private ListView mList;
    public static BillActivity newInstance() {
        return new BillActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Bundle b = getIntent().getExtras();
        mTable = (Table) b.getSerializable(ARG_TABLE_BILL);

        TextView tableName = (TextView) findViewById(R.id.table_name);
        tableName.setText(mTable.getTableName());

        mList = (ListView) findViewById(R.id.table_bill);
        final ArrayAdapter<OrderElement> adapter = new ArrayAdapter<OrderElement>(this,
                android.R.layout.simple_list_item_1, // Layout de cada fila
                mTable.getOrder().getOrderElementList());
        mList.setAdapter(adapter);

        float price = 0;

        for (int i=0; i<mTable.getOrder().getOrderElementList().size(); i++){
            OrderElement orderElement = mTable.getOrder().getOrderElementList().get(i);
            price += orderElement.getPlate().getPrice();
        }
        TextView textViewPrice = (TextView) findViewById(R.id.price);
        textViewPrice.setText(String.format("Precio: %.2f $", price));
    }


}
