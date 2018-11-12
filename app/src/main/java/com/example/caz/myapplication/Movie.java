package com.example.caz.myapplication;

import java.io.Serializable;

public class Movie implements Serializable {
    public static final int NO_ID = -1;

    private String title;
    private int id;
    private boolean watchedMovie = false;          //new checkbox code

    public Movie(int id, String title, boolean watchedMovie) {
        this.title = title;
        this.id = id;
        this.watchedMovie = watchedMovie;
    }

    public boolean isWatchedMovie() {

        return watchedMovie;
    }

    public void setWatchedMovie(boolean watchedMovie) {
        this.watchedMovie = watchedMovie;
    }


    public Movie(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
