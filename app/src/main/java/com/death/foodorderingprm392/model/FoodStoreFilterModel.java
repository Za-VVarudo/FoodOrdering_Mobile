package com.death.foodorderingprm392.model;

import java.io.Serializable;

public class FoodStoreFilterModel implements Serializable {
    private String foodName;
    private Integer foodTypeId;

    public FoodStoreFilterModel(String foodName, Integer foodTypeId) {
        this.foodName = foodName;
        this.foodTypeId = foodTypeId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Integer foodTypeId) {
        this.foodTypeId = foodTypeId;
    }
}
