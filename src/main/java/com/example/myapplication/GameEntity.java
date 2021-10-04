package com.example.myapplication;

public class GameEntity {
    public int imageResId;
    public int titleResId;

    public GameEntity (int imageResId, int titleResId){
        this.imageResId = imageResId;
        this.titleResId = titleResId;
    }

    public int getTitleResId() {
        return imageResId;
    }

    public int getImageResId() {
        return titleResId;
    }
}
