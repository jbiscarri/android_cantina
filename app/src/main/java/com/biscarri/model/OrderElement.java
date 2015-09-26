package com.biscarri.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class OrderElement implements Serializable, Comparable<OrderElement>{
    private String mIdentifier;
    private Table mTable;
    private Plate mPlate;
    private String mComment;
    public static final String ADD_NEW_ELEMENT_ORDER = "com.biscarri.cantina.model.OrderElemt.ADD_NEW_ELEMENT_ORDER";

    public OrderElement(Table table, Plate plate, String comment) {
        mTable = table;
        mPlate = plate;
        mComment = comment;
    }

    public String getIdentifier() {
        return mIdentifier;
    }

    public void setIdentifier(String identifier) {
        mIdentifier = identifier;
    }

    public Table getTable() {
        return mTable;
    }

    public void setTable(Table table) {
        mTable = table;
    }

    public Plate getPlate() {
        return mPlate;
    }

    public void setPlate(Plate plate) {
        mPlate = plate;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    @Override
    public String toString() {
        String toStringValue = getPlate().getName() + " (" + getPlate().getPrice() + "$)";
        if (getComment().length() > 0)
            toStringValue =  toStringValue + " - " +getComment();
        return toStringValue;
    }

    @Override
    public int compareTo(@NonNull OrderElement another) {
        return getPlate().getName().compareToIgnoreCase(another.getPlate().getName());
    }
}
