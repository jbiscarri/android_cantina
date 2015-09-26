package com.biscarri.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by joanbiscarri on 17/09/15.
 */
public class Allergen implements Serializable, Comparable<Allergen>{
    private int mId;
    private String mName;
    private String mImage;

    public Allergen(int id, String name, String image) {
        mId = id;
        mName = name;
        mImage = image;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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
