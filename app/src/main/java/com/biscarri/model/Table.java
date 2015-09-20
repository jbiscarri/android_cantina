package com.biscarri.model;

import java.io.Serializable;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class Table implements Serializable{
    private int mIdentifier;
    private String mTableName;
    private Order mOrder;

    public Table(int identifier, String tableName) {
        mIdentifier = identifier;
        mTableName = tableName;
        mOrder = new Order(this);
    }

    public int getIdentifier() {
        return mIdentifier;
    }

    public void setIdentifier(int identifier) {
        mIdentifier = identifier;
    }

    public String getTableName() {
        return mTableName;
    }

    public void setTableName(String tableName) {
        mTableName = tableName;
    }

    public Order getOrder() {
        return mOrder;
    }

    public void setOrder(Order order) {
        mOrder = order;
    }

    @Override
    public String toString() {
        return getTableName();
    }
}
