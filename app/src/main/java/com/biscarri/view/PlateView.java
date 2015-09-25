package com.biscarri.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.biscarri.cantina.R;
import com.biscarri.model.Plate;

/**
 * Created by joanbiscarri on 22/09/15.
 */
public class PlateView extends android.support.v7.widget.CardView {

    private TextView mPlateName;
    private ImageView mPlateImage;
    private Plate mPlate;

    public PlateView(Context context) {
        this(context, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);

    }

    public PlateView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_plate, this, true);

        mPlateName = (TextView) findViewById(R.id.plate_name);
        mPlateImage = (ImageView) findViewById(R.id.plateImageView);
    }


    public TextView getPlateName() {
        return mPlateName;
    }

    public void setPlateName(TextView plateName) {
        mPlateName = plateName;
    }

    public ImageView getPlateImage() {
        return mPlateImage;
    }

    public void setPlateImage(ImageView plateImage) {
        mPlateImage = plateImage;
    }

    public Plate getPlate() {
        return mPlate;
    }

    public void setPlate(Plate plate, Context context) {
        mPlate = plate;
        int imageId = context.getResources().getIdentifier(plate.getImage(), "drawable", context.getPackageName());
        mPlateImage.setImageResource(imageId);

        mPlateName.setText(mPlate.getName());
    }
}
