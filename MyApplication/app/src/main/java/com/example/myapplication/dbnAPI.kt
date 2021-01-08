package com.example.myapplication

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

interface dbnAPI {

    @GET("login")
    fun getuserinfo(@Query("username") username: String):
            Observable<dbnModels.Result>

    @FormUrlEncoded
    @POST("members/login")
    fun postTest(@Field("username") param: String):
            Observable<String>

    companion object {
        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun create(): dbnAPI {

//            val retrofit = Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .baseUrl("http://13.125.249.162:5000/")
//                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create() )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://13.125.249.162:5000/")
                .build()

            return retrofit.create(dbnAPI::class.java)
        }
    }


}