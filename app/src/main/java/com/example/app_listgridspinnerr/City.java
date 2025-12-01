package com.example.app_listgridspinnerr;

import java.io.Serializable;

public class City implements Serializable {
    private String name;
    private int imageResourceId;
    private String description;

    public City(String name, int imageResourceId, String description) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getDescription() {
        return description;
    }
}
