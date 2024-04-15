package com.example.health_tracker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerApi {
    @GET("/users/get")
    Call<UserGetResponse> getUser(
            @Query("username") String username,
            @Query("password") String password
    );
}
