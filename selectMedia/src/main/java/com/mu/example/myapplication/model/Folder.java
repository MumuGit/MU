package com.mu.example.myapplication.model;

import java.util.ArrayList;

/**
 * Created by mu on 2018/3/5.
 */

public class Folder {

    public Folder(String name) {
        this.name = name;
    }

    private String name;

    private int count;

    public String getName() {
        return name;
    }

    private ArrayList<Media> medias = new ArrayList<>();

    public ArrayList<Media> getMedias() {
        return medias;
    }

    public void add(Media media) {
        medias.add(media);
    }

}
