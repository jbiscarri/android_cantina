package com.biscarri.model;

/**
 * Created by joanbiscarri on 17/09/15.
 */
public class Allergen {
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
}
