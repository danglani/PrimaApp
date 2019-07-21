package com.example.primaapp.network.api;


import com.example.primaapp.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static Gson gson;
    private static APICalls apiInterface;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }
    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }

        return gson;
    }

    public synchronized static APICalls getApiClient() {
        if (apiInterface == null) {
            apiInterface = getClient().create(APICalls.class);
        }

        return apiInterface;
    }
}
