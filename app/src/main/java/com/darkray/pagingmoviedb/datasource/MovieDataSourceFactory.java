package com.darkray.pagingmoviedb.datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.darkray.pagingmoviedb.model.Movie;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie>{

    private CompositeDisposable compositeDisposable;
    private MutableLiveData<MovieDataSource> movieDataSourceMutableLiveData = new MutableLiveData<>();

    public MovieDataSourceFactory(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MovieDataSource movieDataSource = new MovieDataSource(compositeDisposable);
        movieDataSourceMutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMovieDataSourceMutableLiveData() {
        return movieDataSourceMutableLiveData;
    }
}
