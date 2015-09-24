package com.biscarri.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by joanbiscarri on 17/09/15.
 */
public class Plate implements Comparable<Plate> {
    private String mName;
    private String mImage;
    private float mPrice;
    private List<Allergen> mAllergenList;

    public Plate(String name, String image, float price, List<Allergen> allergenList) {
        mName = name;
        mImage = image;
        mPrice = price;
        mAllergenList = allergenList;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public List<Allergen> getAllergenList() {
        return mAllergenList;
    }

    public void setAllergenList(List<Allergen> allergenList) {
        mAllergenList = allergenList;
    }

    @Override
    public int compareTo(@NonNull Plate another) {
        return getName().compareToIgnoreCase(another.getName());
    }

}
