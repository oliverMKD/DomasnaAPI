package com.oliver.domasnaapi.api.api;

import android.content.Context;

import com.oliver.domasnaapi.BuildConfig;
import com.oliver.domasnaapi.interceptor.LoggingInterceptor;
import com.oliver.domasnaapi.modeli.NYTimesModel;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oliver on 1/16/2018.
 */

public class RestApi {

    public static final int request_max_time_in_secconds = 20;
    private Context activity;

    public RestApi(Context context) {
        this.activity = activity;
    }

    public Retrofit getRetrofitInstance(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .readTimeout(request_max_time_in_secconds, TimeUnit.SECONDS)
                .connectTimeout(request_max_time_in_secconds,TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public ApiService request(){
        return getRetrofitInstance().create(ApiService.class);
    }

    public Call<NYTimesModel> getPhotos(String feature){
        return request().getPhotos(feature);
    }
    public Call<NYTimesModel> getTitle(String feature){
        return request().getTitle(feature);
    }
    public Call<NYTimesModel> getSection(String feature){
        return request().getSection(feature);
    }
}
