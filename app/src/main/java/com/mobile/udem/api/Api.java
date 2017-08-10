package com.mobile.udem.api;

import com.google.gson.Gson;
import com.mobile.udem.models.ApiErrorResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by osmar on 08-04-17.
 */

public class Api {

    private final static String URL = "http://udemservices.udem.edu.ni/api";

    private static String getBase() {
        return URL + "/";
    }

    public static ApiInterface instance() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Api.getBase())
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiInterface.class);
    }

    /**
     * To serialize error
     * @param errorBody
     * @return
     */
    public static ApiErrorResponse parseError(ResponseBody errorBody) {
        Gson gson = new Gson();
        return gson.fromJson(errorBody.charStream(), ApiErrorResponse.class);
    }

    /**
     * To set timeout
     * @return
     */
    private static OkHttpClient getRequestHeader() {
        Builder builder = new Builder();
        builder.readTimeout(6000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(6000, TimeUnit.MILLISECONDS);
        return builder.build();
    }
}
