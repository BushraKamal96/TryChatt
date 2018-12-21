package com.example.acer.trychatt.model;

/**
 * Created by acer on 9/4/2018.
 */

public class callModel {
    private int image;
    private String name,number;

    public callModel() {
    }

    public callModel(int image, String name, String number) {
        this.image = image;
        this.name = name;
        this.number = number;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

