package com.death.foodorderingprm392.data;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {
    @JsonSetter("Id")
    private long id;
    @JsonSetter("Name")
    private String name;
    @JsonSetter("Address")
    private String address;
    @JsonSetter("Latitude")
    private String latitude;
    @JsonSetter("Longitude")
    private String longitude;
    @JsonSetter("ImageSrc")
    private String imageSrc;
    @JsonSetter("FoodStores")
    private ArrayList<FoodStore> foodStores;

    public Store() {
    }

    public Store(long id, String name, String address, String latitude, String longitude, String imageSrc) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageSrc = imageSrc;
    }

    public Store(long id, String name, String address, String latitude, String longitude, String imageSrc, ArrayList<FoodStore> foodStores) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageSrc = imageSrc;
        this.foodStores = foodStores;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public ArrayList<FoodStore> getFoodStores() {
        return foodStores;
    }

    public void setFoodStores(ArrayList<FoodStore> foodStores) {
        this.foodStores = foodStores;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}
