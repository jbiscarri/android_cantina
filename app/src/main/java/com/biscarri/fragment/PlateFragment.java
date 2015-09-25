package com.biscarri.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.biscarri.cantina.R;
import com.biscarri.model.OrderElement;
import com.biscarri.model.Plate;
import com.biscarri.model.Plates;
import com.biscarri.view.PlateView;

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
 * Created by joanbiscarri on 20/09/15.
 */
public class PlateFragment extends Fragment {
    private RecyclerView mPlateRecicler;
    private PlateAdapter mPlateAdapter;
    private ProgressBar mProgressBar;
    private PlatesBroadcastReceiver mBroadcastReceiver;

    public static PlateFragment newInstance() {
        return new PlateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_plate, container, false);

        mProgressBar = (ProgressBar) root.findViewById(R.id.progress);
        mPlateRecicler = (RecyclerView) root.findViewById(R.id.plates);
        mPlateRecicler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPlateRecicler.setItemAnimator(new DefaultItemAnimator());
        mPlateAdapter = new PlateAdapter();
        mPlateRecicler.setAdapter(mPlateAdapter);

        mBroadcastReceiver = new PlatesBroadcastReceiver(mPlateAdapter);
        // Me suscribo a notificaciones broadcast
        getActivity().registerReceiver(
                mBroadcastReceiver,
                new IntentFilter(Plates.PLATES_UPDATED));

        return root;
    }

    protected class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.PlateViewHolder> {

        public PlateAdapter() {
            super();
        }

        @Override
        public PlateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PlateViewHolder(new PlateView(getActivity()));
        }

        @Override
        public void onBindViewHolder(PlateViewHolder holder, int position) {
            Plate currentPlate = Plates.getInstance().getPlates().get(position);
            holder.bindPlate(currentPlate);
        }

        @Override
        public int getItemCount() {
            int plates = Plates.getInstance().getPlates().size();
            if (plates > 0){
                mProgressBar.setVisibility(View.GONE);
            }
            return Plates.getInstance().getPlates().size();
        }

        // ViewHolder que maneja una vista
        protected class PlateViewHolder extends RecyclerView.ViewHolder {
            private PlateView mPlateView;

            public PlateViewHolder(View itemView) {
                super(itemView);
                mPlateView = (PlateView) itemView;
            }

            // Asocio el modelo con ViewHolder
            public void bindPlate(final Plate plate) {
                mPlateView.setPlate(plate, getActivity());
                mPlateView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Show dialog
                        AddPlateDialogFragment dialog = new AddPlateDialogFragment();
                        dialog.setPlate(plate);
                        dialog.setAddPlateDialogListener((AddPlateDialogFragment.AddPlateDialogListener) getActivity());
                        dialog.show(getFragmentManager(), null);

                    }
                });
            }
        }
    }

    private class PlatesBroadcastReceiver extends BroadcastReceiver {
        private PlateAdapter mAdapter;

        // Necesito el adapter al que voy a avisar de que hay nuevos datos
        public PlatesBroadcastReceiver(PlateAdapter adapter) {
            super();
            mAdapter = adapter;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            mAdapter.notifyDataSetChanged();
        }
    }

}
