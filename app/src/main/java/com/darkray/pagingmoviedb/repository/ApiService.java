package com.darkray.pagingmoviedb.repository;


import com.darkray.pagingmoviedb.model.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(AllApi.TOP_RATED_MOVIE)
    Single<MovieResponse> getTopRatedMovies(@Query("page") int page, @Query("api_key") String apiKey);
}