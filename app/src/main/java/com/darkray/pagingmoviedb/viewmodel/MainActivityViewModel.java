package com.darkray.pagingmoviedb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.darkray.pagingmoviedb.datasource.MovieDataSource;
import com.darkray.pagingmoviedb.datasource.MovieDataSourceFactory;
import com.darkray.pagingmoviedb.enums.State;
import com.darkray.pagingmoviedb.model.Movie;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {

    private LiveData<PagedList<Movie>> moviePagedList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MovieDataSourceFactory movieDataSourceFactory;

    public MainActivityViewModel() {

        movieDataSourceFactory = new MovieDataSourceFactory(compositeDisposable);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(MovieDataSource.PAGE_SIZE)
                        .build();

        moviePagedList = (new LivePagedListBuilder(movieDataSourceFactory, pagedListConfig)).build();
    }


    public LiveData<State> getState(){
        return Transformations.switchMap(movieDataSourceFactory.getMovieDataSourceMutableLiveData(), MovieDataSource::getStateMutableLiveData);
    }

    public void retry(){
        movieDataSourceFactory.getMovieDataSourceMutableLiveData().getValue().retry();
    }

    public boolean listEmpty(){
        if(moviePagedList.getValue() != null){
            return moviePagedList.getValue().isEmpty();
        }
        else{
            return true;
        }
    }

    public LiveData<PagedList<Movie>> getMoviePagedList() {
        return moviePagedList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
