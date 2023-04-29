package com.udacity.asteroidradar.api

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.APIKEY
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.*


//  Moshi Adapter
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object PictureApi {
    val PictureRetrofitService :PictureApiService by lazy { retrofit.create(PictureApiService::class.java)


    }

interface PictureApiService {
    @GET("planetary/apod?api_key="+APIKEY)

        fun getProperties():Call<PictureOfDay>

}
}


