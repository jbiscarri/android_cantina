package com.biscarri.model;

import android.widget.ProgressBar;

import com.biscarri.cantina.R;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class Plates {

    public static final String PLATES_UPDATED = "com.biscarri.cantina.model.Plates.PLATES_UPDATED";


    private static Plates platesInstance = new Plates();

    private List<Plate> mPlates;

    public static Plates getInstance() {
        return platesInstance;
    }

    public Plates() {
        mPlates = new LinkedList<>();
        //TODO select allergens

        mPlates.add(new Plate(1, "Hamburger", "hamburger", 12.50f, null, "A description"));
        mPlates.add(new Plate(2, "Paella", "paella", 12.50f, null, "A description"));
        mPlates.add(new Plate(3, "Chips", "chips", 12.50f, null, "A description"));

    }

    public List<Plate> getPlates() {
        return mPlates;
    }

    public void setPlates(List<Plate> plates) {
        mPlates = plates;
    }


    public Plate getPlateWithId(int id) {
        for (int i = 0; i<mPlates.size(); i++) {
            Plate p = mPlates.get(i);
            if (p.getPlateId() == id)
                return p;
        }
        return null;
    }

}
