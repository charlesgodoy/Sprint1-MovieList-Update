package com.example.caz.myapplication;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> movieList;
    private MovieRepository repo;

    public LiveData<ArrayList<Movie>> getMoviesList() {
        if(movieList == null) {      // null meaning no value yet
            loadList();
        }
        return movieList;
    }


    // private so no one can create it and void cause value is stored here, not returned
    // also, can only get here if movieList is null
    private void loadList() {
        repo = new MovieRepository();
        movieList = repo.getMovies();
    }

    //method that accepts a movie
    public void addMovie(Movie movie) {
        if(movieList != null) {
            movieList.setValue(repo.addMovie(movie));
        }
    }


// Temporary code to for testing delete

//    public void removeMovie(Movie movie) {
//        if(movieList != null) {
//            movieList.setValue(repo.removeMovie(movie));
//        }
//    }


}
