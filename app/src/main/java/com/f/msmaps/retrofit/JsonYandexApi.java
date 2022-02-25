package com.f.msmaps.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonYandexApi {
    @GET("?apikey")
    public Call<MyPojo> getMyResponse(@Query("apikey") String key,
                                      @Query("format") String format,
                                      @Query("lat") double lat,
                                      @Query("lng") double lng,
                                      @Query("distance") int distance,
                                      @Query("lang") String lang);
}
