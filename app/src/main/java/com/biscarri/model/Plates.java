package com.biscarri.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class Plates {

    private static Plates platesInstance = new Plates();

    private List<Plate> mPlates;

    public static Plates getInstance() {
        return platesInstance;
    }

    public Plates() {
        mPlates = new LinkedList<>();
        //TODO select allergens

        mPlates.add(new Plate("Hamburger", "hamburger01", 12.50f, null));
    }

    public List<Plate> getPlates() {
        return mPlates;
    }
}
