package com.biscarri.model;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class OrderElement {
    private String mIdentifier;
    private Table mTable;
    private Plate mPlate;
    private String mComment;

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
        return getPlate().getName();
    }
}
