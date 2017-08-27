package com.mobile.udem.api;

import com.mobile.udem.models.Auth;
import com.mobile.udem.models.History;
import com.mobile.udem.models.Notes;
import com.mobile.udem.models.Profile;
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
     *http://udemservices.udem.edu.ni/api/Login
     * @param auth
     * @return User
     */
    @POST("Login")
    Call<User> signIn(@Body Auth auth);

    /*
    http://udemservices.udem.edu.ni/api/Historial/170000
   */
    @GET("Historial/{usuario}")
    Call<List<History>> getHistory(@Path("usuario") String code);

    /*
    http://udemservices.udem.edu.ni/api/Calificaciones/121388
   */
    @GET("Calificaciones/{usuario}")
    Call<List<Notes>> getNotes(@Path("usuario") String code);
    /*
    http://udemservices.udem.edu.ni/api/Inforestudiante/121388
   */
    @GET("Inforestudiante/{usuario}")
    Call<Profile> getProfile(@Path("usuario") String code);
}
