package com.death.foodorderingprm392.data;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

public class Food implements Serializable {
    @JsonSetter("Id")
    private long id;
    @JsonSetter("Name")
    private String name;
    @JsonSetter("FoodTypeId")
    private int foodTypeId;
    @JsonSetter("FoodTypeName")
    private String foodTypeName;
    @JsonSetter("ImageSrc")
    private String imageSrc;
    @JsonSetter("IsAvailable")
    private boolean isAvailable;

    public Food() {
    }

    public Food(long id, String name, int foodTypeId, String foodTypeName, String imageSrc, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.foodTypeId = foodTypeId;
        this.foodTypeName = foodTypeName;
        this.imageSrc = imageSrc;
        this.isAvailable = isAvailable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
