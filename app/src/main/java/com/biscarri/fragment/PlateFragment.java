package com.biscarri.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biscarri.cantina.R;
import com.biscarri.model.Plate;
import com.biscarri.view.PlateView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class PlateFragment extends Fragment{
    private RecyclerView mPlateRecicler;
    private List<Plate> mPlateList;

    public static PlateFragment newInstance() {
        return new PlateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_plate, container, false);

        Plate p = new Plate("Plate1", null, 30.9f, null);
        Plate p2 = new Plate("Plate2", null, 30.9f, null);
        Plate p3 = new Plate("Plate3", null, 30.9f, null);

        LinkedList<Plate> plates  = new LinkedList<Plate>();
        plates.add(p);
        plates.add(p2);
        plates.add(p3);
        setPlateList(plates);

        mPlateRecicler = (RecyclerView) root.findViewById(R.id.plates);
        mPlateRecicler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPlateRecicler.setItemAnimator(new DefaultItemAnimator());
        mPlateRecicler.setAdapter(new PlateAdapter(mPlateList));

        return root;
    }

    public void setPlateList(List<Plate> plateList) {
        mPlateList = plateList;
    }




    protected class PlateAdapter extends RecyclerView.Adapter<PlateAdapter.PlateViewHolder> {
        private List<Plate> mPlateList;

        public PlateAdapter(List<Plate> plates) {
            super();
            mPlateList = plates;
        }

        @Override
        public PlateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PlateViewHolder(new PlateView(getActivity()));
        }

        @Override
        public void onBindViewHolder(PlateViewHolder holder, int position) {
            Plate currentPlate = mPlateList.get(position);
            holder.bindPlate(currentPlate);
        }

        @Override
        public int getItemCount() {
            return mPlateList.size();
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
                mPlateView.setPlate(plate);
                mPlateView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //Show dialog
                    }
                });
            }
        }
    }
}
