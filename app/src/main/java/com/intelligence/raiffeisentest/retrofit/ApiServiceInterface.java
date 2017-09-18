package com.intelligence.raiffeisentest.retrofit;

import com.intelligence.raiffeisentest.models.UsersList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiServiceInterface {

    @GET("api/?seed=abc")
    Call<UsersList> getUsers(@Query("page") int pageIndex,
                             @Query("results") int resultsNumber);

}
