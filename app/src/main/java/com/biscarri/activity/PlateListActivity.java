package com.biscarri.activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.biscarri.cantina.R;
import com.biscarri.fragment.AddPlateDialogFragment;
import com.biscarri.fragment.PlateFragment;
import com.biscarri.model.OrderElement;
import com.biscarri.model.Plate;
import com.biscarri.model.Plates;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanbiscarri on 22/09/15.
 */
public class PlateListActivity extends AppCompatActivity  implements AddPlateDialogFragment.AddPlateDialogListener  {
    PlateFragment mPlateFragment = PlateFragment.newInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_list);

        if (Plates.getInstance().getPlates().size() == 0) {
            updatePlates();
        }

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_plate_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_plate_list, mPlateFragment)
                    .commit();
        }
    }

    @Override
    public void onPlateAddedListener(AddPlateDialogFragment dialog, Plate plate, String comment) {
        //Add plate to Order
        //This controller don't have the table or order, so, I broadcast it

        Intent broadcast = new Intent(OrderElement.ADD_NEW_ELEMENT_ORDER);
        broadcast.putExtra("PLATE_ID", plate.getPlateId());
        broadcast.putExtra("COMMENT", comment);
        this.sendBroadcast(broadcast);

        this.finish();
    }

    public void updatePlates() {
        AsyncTask<String, Integer, List<Plate>> updateTask = new PlatesDownloader(this);
        updateTask.execute();
    }

    private class PlatesDownloader extends AsyncTask<String, Integer, List<Plate>> {
        private Context mContext;

        public PlatesDownloader(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Plate> doInBackground(String... params) {
            LinkedList<Plate> plates = new LinkedList<>();
            URL url;
            InputStream input = null;
            try {
                url = new URL("http://www.mocky.io/v2/56048f4214d5de7d0b2a81aa");
                HttpURLConnection con = (HttpURLConnection) (url.openConnection());
                con.setConnectTimeout(5000);
                con.connect();
                int responseLength = con.getContentLength();
                byte data[] = new byte[1024];
                long currentBytes  = 0;
                int downloadedBytes;
                input = con.getInputStream();
                StringBuilder sb = new StringBuilder();
                while ((downloadedBytes = input.read(data)) != -1) {
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }

                    sb.append(new String(data, 0, downloadedBytes));

                    if (responseLength > 0) {
                        currentBytes += downloadedBytes;
                        publishProgress((int) ((currentBytes * 100) / responseLength));
                    }
                    else {
                        currentBytes++;
                        publishProgress((int)currentBytes * 10);
                    }

                }

                JSONObject jsonRoot = new JSONObject(sb.toString());
                JSONArray platesJSON = jsonRoot.getJSONArray("plates");

                for (int i = 0; i < platesJSON.length(); i++) {
                    JSONObject plateJSON = platesJSON.getJSONObject(i);

                    int id = (int) plateJSON.getInt("id");
                    String name = plateJSON.getString("name");
                    String image = plateJSON.getString("image");
                    float price = (float) plateJSON.getDouble("price");
                    String description = plateJSON.getString("description");
                    JSONArray list = plateJSON.getJSONArray("allergens");
                    //TODO create Allergen list
                    Plate plate = new Plate(id, name, image, price, null, description);
                    plates.add(plate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return plates;
        }

        @Override
        protected void onPostExecute(List<Plate> plates) {
            super.onPostExecute(plates);

            if (plates != null && plates.size() > 0) {
                Plates.getInstance().setPlates(plates);
                Intent broadcast = new Intent(Plates.PLATES_UPDATED);
                mContext.sendBroadcast(broadcast);

            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setNegativeButton(android.R.string.ok, null);
                alert.setMessage("Error al descargar los platos");
                alert.show();
            }
        }
    }
}
