package com.mobile.udem.api;

import com.mobile.udem.models.Auth;
import com.mobile.udem.models.History;
import com.mobile.udem.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by osmar on 08-04-17.
 */

public interface ApiInterface {

    /**
     * Make http request to get an user
     *
     * @param auth
     * @return AccessTokenModel
     */
    @POST("Login")
    Call<User> signIn(@Body Auth auth);

    /*
    http://udemservices.udem.edu.ni/api/Historial/121388
   */
    @GET("Historial/{usuario}")
    Call<List<History>> getHistory(@Path("usuario") String code);
}
