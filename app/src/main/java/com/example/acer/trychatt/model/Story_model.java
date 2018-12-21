package com.example.acer.trychatt.model;

/**
 * Created by acer on 9/4/2018.
 */

public class Story_model {
    private int image;
    private String name;

    public Story_model() {
    }

    public Story_model(int image, String name) {
        this.image = image;
        this.name = name;
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
}
