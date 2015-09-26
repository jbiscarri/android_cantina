package com.biscarri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.biscarri.cantina.R;
import com.biscarri.model.Allergen;
import com.biscarri.model.Allergens;

import java.util.List;

/**
 * Created by joanbiscarri on 27/09/15.
 */
public class AllergensAdapter extends ArrayAdapter<Allergen> {
    private final Context mContext;
    private final List<Allergen> mAllergens;

    public AllergensAdapter(Context context,List<Allergen> objects) {
        super(context, -1, objects);
        this.mContext = context;
        this.mAllergens = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.view_allergen, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.allergen_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.allergen_image);

        Allergen allergen = Allergens.getInstance().getAllergens().get(position);
        int imageId = mContext.getResources().getIdentifier(allergen.getImage(), "drawable", mContext.getPackageName());
        imageView.setImageResource(imageId);

        textView.setText(allergen.getName());

        return rowView;
    }
}
