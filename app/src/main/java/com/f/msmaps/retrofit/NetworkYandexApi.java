package com.f.msmaps.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkYandexApi {
    private static NetworkYandexApi mInstance;
    private static final String BASE_URL = "https://api.rasp.yandex.net/v3.0/nearest_stations/";
    private Retrofit retrofit;

    private NetworkYandexApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkYandexApi getInstance() {
        if (mInstance == null)
            mInstance = new NetworkYandexApi();

        return mInstance;
    }

    public JsonYandexApi getJsonApi() {
        return retrofit.create(JsonYandexApi.class);
    }
}
