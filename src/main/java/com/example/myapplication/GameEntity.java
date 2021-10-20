package com.example.myapplication;

import java.io.Serializable;

//    public int imageResId;
//    public int titleResId;
//
//    public GameEntity (int imageResId, int titleResId){
//        this.imageResId = imageResId;
//        this.titleResId = titleResId;
//    }

public class GameEntity implements Serializable {

    private int Id;
    private String Name;
    private double Rating;
    private String Released;
    private String BackgroundImage;

    public GameEntity() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public String getReleased() {

        return Released;
    }

    public void setReleased(String releaseDate) {
        Released = releaseDate;
    }

    public String getBackgroundImage() {
        return BackgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        BackgroundImage = backgroundImage;
    }
}
