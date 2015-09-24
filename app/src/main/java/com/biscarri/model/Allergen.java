package com.biscarri.model;

import android.support.annotation.NonNull;

/**
 * Created by joanbiscarri on 17/09/15.
 */
public class Allergen implements Comparable<Allergen>{
    private String mName;
    private String mImage;

    public Allergen(String name, String image) {
        mName = name;
        mImage = image;
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
    
    @Override
    public int compareTo(@NonNull Allergen another) {
        return getName().compareToIgnoreCase(another.getName());
    }
}
