package com.example.ytsook.ex4;
import com.google.gson.JsonElement;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by ytsook on 5/27/2018.
 */

public interface service {

    @Headers("Authorization: Client-ID " + "4f02100edf90e85")

    //TODO make sure album name
    @GET("/album/{myPath}")
    void getGallery(@Path("myPath") String id, Callback<JsonElement> cb);
}
