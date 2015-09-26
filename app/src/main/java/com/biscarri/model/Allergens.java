package com.biscarri.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanbiscarri on 26/09/15.
 */
public class Allergens {
    private static Allergens allergensInstance = new Allergens();

    private List<Allergen> mAllergens;

    public static Allergens getInstance() {
        return allergensInstance;
    }

    public Allergens() {
        mAllergens = new LinkedList<>();
        mAllergens.add(new Allergen(1, "Gluten", "gluten"));
        mAllergens.add(new Allergen(2, "Huevo", "huevo"));
        mAllergens.add(new Allergen(3, "Lactosa", "lactosa"));
    }

    public List<Allergen> getAllergens() {
        return mAllergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        mAllergens = allergens;
    }

    public Allergen getAllergenWithId(int id) {
        for (int i = 0; i<mAllergens.size(); i++) {
            Allergen a = mAllergens.get(i);
            if (a.getId() == id)
                return a;
        }
        return null;
    }

}
