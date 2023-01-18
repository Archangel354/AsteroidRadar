package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
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
    @GET("planetary/apod?api_key=ZYylBzfTBo7ZrIOHItyqefWq9OdE7h2lQGUk476L")
        fun getProperties():Call<PictureOfDay>
}
}


