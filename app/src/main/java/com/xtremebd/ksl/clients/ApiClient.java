package com.xtremebd.ksl.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mashnoor on 8/14/17.
 */

public class ApiClient {
    private static final String STATIC_BASE_URL = "http://128.199.157.233/kslbackend/";
    private static final String DYNAMIC_BASE_URL = "http://128.199.157.233:5003/";
    private static Retrofit staticRetrofit = null, dynamicRetrofit=null;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private ApiClient() {} // So that nobody can create an object with constructor

    public static synchronized Retrofit getStaticClient() {
        if (staticRetrofit==null) {
            staticRetrofit = new Retrofit.Builder()
                    .baseUrl(STATIC_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return staticRetrofit;
    }
    public static synchronized Retrofit getDynamicClient() {
        if (dynamicRetrofit==null) {
            dynamicRetrofit = new Retrofit.Builder()
                    .baseUrl(DYNAMIC_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return dynamicRetrofit;
    }
}
