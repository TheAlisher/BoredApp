package com.example.todo.data;

import android.util.Log;

import com.example.todo.model.BoredAction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BoredAPIClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    BoredApi client = retrofit.create(BoredApi.class);

    public void getAction(
            String type,
            Float minPrice,
            BoredActionCallback callback
    ) {
        Call<BoredAction> call = client.getAction(
                type,
                minPrice
        );

        Log.d("anim", call.request().url().toString());

        call.enqueue(new Callback<BoredAction>() {
            @Override
            public void onResponse(Call<BoredAction> call, Response<BoredAction> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFailure(new Exception("Body is empty"));
                    }
                } else {
                    callback.onFailure(new Exception("Response code " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<BoredAction> call, Throwable t) {
                callback.onFailure(new Exception(t));
            }
        });
    }

    public interface BoredActionCallback extends BaseCallback<BoredAction> { }

    public interface BaseCallback<T> {
        void onSuccess(T result);
        void onFailure(Exception E);
    }

    private interface BoredApi {
        @GET("api/activity/")
        Call<BoredAction> getAction(
                @Query("type") String type,
                @Query("minprice") Float minPrice
        );
    }
}
