package com.biscarri.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class Order {
    private List<OrderElement> mOrderElementList;
    private Table mTable;

    public Order(Table table) {
        mOrderElementList = new LinkedList<>();
        mTable = table;
    }

    public Order(List<OrderElement> orderElementList, Table table) {
        mOrderElementList = orderElementList;
        mTable = table;
    }

    public List<OrderElement> getOrderElementList() {
        return mOrderElementList;
    }

    public void setOrderElementList(List<OrderElement> orderElementList) {
        mOrderElementList = orderElementList;
    }

    public Table getTable() {
        return mTable;
    }

    public void setTable(Table table) {
        mTable = table;
    }
}
