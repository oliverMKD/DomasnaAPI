package com.oliver.domasnaapi.api.api;




import com.oliver.domasnaapi.modeli.NYTimesModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Oliver on 1/16/2018.
 */

public interface ApiService {
//    @GET("title?"+ApiConstants.Api_key)
//    Call<NYTimesModel> getTitle(@Query("feature") String featureString);

    @GET("home.json?"+ApiConstants.Api_key)
    Call<NYTimesModel> getUrl(@Query("") String featureString);

    @GET(ApiConstants.PHOTOS_ENDPOINT+"search?"+ApiConstants.Api_key)
    Call<NYTimesModel> getStoriesSearch(@Query("term") String featureString);

    @GET("{link}")
    Call<NYTimesModel> getStoriesRefreshSearch(@Path("link") String link);
//
//    @GET("section?"+ApiConstants.Api_key)
//    Call<NYTimesModel> getSection(@Query("feature") String featureString);


//    @FormUrlEncoded
//    @POST("photos")
//    Call<Photo> uploadPhoto(@Field("name") String stringName, @Body Photo photo);
}
