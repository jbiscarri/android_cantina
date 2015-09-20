package com.biscarri.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.biscarri.cantina.R;
import com.biscarri.model.Plate;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class PlateFragment extends Fragment{
    private TextView mPlateName;
    private ImageView mPlateImage;
    private Plate mPlate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_plate, container, false);

        mPlateName = (TextView) root.findViewById(R.id.plate_name);
        mPlateImage = (ImageView) root.findViewById(R.id.plateImageView);

        return root;
    }
}
