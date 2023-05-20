package com.example.health_tracker;

import android.util.Log;

import com.example.health_tracker.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {
    private User user;
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private final ServerApi api = retrofit.create(ServerApi.class);

    public User getUser(String username, String password) {
        api.getUser(username, password).enqueue(new Callback<UserGetResponse>() {
            @Override
            public void onResponse(Call<UserGetResponse> call, Response<UserGetResponse> response) {
                if (response.isSuccessful()) {
                    user = response.body().getUser();
                    Log.d("DATA REPO", String.valueOf(user == null));
                }
            }

            @Override
            public void onFailure(Call<UserGetResponse> call, Throwable t) {
                Log.d("DATA REPO", t.toString());
            }
        });

        return user;
    }
}
