package com.biscarri.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanbiscarri on 20/09/15.
 */
public class Tables {

    private static Tables tablesInstance = new Tables();

    private List<Table> mTables;

    public static Tables getInstance() {
        return tablesInstance;
    }

    public Tables() {
        mTables = new LinkedList<>();
        mTables.add(new Table(1, "Table 1"));
        mTables.add(new Table(2, "Table 2"));
        mTables.add(new Table(3, "Table 3"));
        mTables.add(new Table(4, "Table 4"));
    }

    public List<Table> getTables() {
        return mTables;
    }
}
