package com.darkray.pagingmoviedb.ui.activity.mainActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.darkray.pagingmoviedb.adapter.MovieItemAdapter;
import com.darkray.pagingmoviedb.datasource.MovieDataSource;
import com.darkray.pagingmoviedb.datasource.MovieDataSourceFactory;
import com.darkray.pagingmoviedb.enums.State;
import com.darkray.pagingmoviedb.model.Movie;

import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends AndroidViewModel {

    final LiveData<PagedList<Movie>> moviePagedList;
    final LiveData<State> stateObserver;
    public final MovieItemAdapter adapter;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MovieDataSourceFactory movieDataSourceFactory;

    public MainViewModel(@NonNull Application application) {
        super(application);
        movieDataSourceFactory = new MovieDataSourceFactory(compositeDisposable);
        adapter = new MovieItemAdapter();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(MovieDataSource.PAGE_SIZE)
                        .build();

        moviePagedList = (new LivePagedListBuilder(movieDataSourceFactory, pagedListConfig)).build();
        stateObserver = Transformations.switchMap(movieDataSourceFactory.getMovieDataSourceMutableLiveData(), MovieDataSource::getStateMutableLiveData);
    }


    public void retry() {
        if(movieDataSourceFactory.getMovieDataSourceMutableLiveData().getValue() != null){
            movieDataSourceFactory.getMovieDataSourceMutableLiveData().getValue().retry();
        }
    }

    public boolean listEmpty() {
        if (moviePagedList.getValue() != null) {
            return moviePagedList.getValue().isEmpty();
        } else {
            return true;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
