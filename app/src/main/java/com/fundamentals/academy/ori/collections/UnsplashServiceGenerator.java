package com.fundamentals.academy.ori.collections;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 15/12/2017.
 */

public class UnsplashServiceGenerator {

    private static final String baseUrl = "https://api.unsplash.com";

    private static final String clientKey = "10f997d269fc66eeab9f6a0f67aab805b64632a8979b0e230fe05202359383ec";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static UnsplashService generateService(){
        return retrofit.create(UnsplashService.class);
    }

    public interface UnsplashService{
        @GET("/collections/curated?client_id="+clientKey)
        Call<List<CollectionsResult>> getCollectionsCurated(@Query("page") Integer page, @Query("per_page") Integer per_page);
    }
}
