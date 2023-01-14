package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


//  Moshi Adapter
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object AsteroidApi {
    val retrofitService : AsteroidApiService by lazy { retrofit.create(AsteroidApiService::class.java)
    }

interface AsteroidApiService {
    @GET("planetary/apod?api_key=ZYylBzfTBo7ZrIOHItyqefWq9OdE7h2lQGUk476L")
        fun getProperties():Call<PictureOfDay>

//    fun getProperties(): Call<List<PictureOfDay>>
}


}


//fun parseAsteroidsJsonResult(jsonResult: JSONObject): ArrayList<Asteroid> {
//    val nearEarthObjectsJson = jsonResult.getJSONObject("near_earth_objects")
//
//    val asteroidList = ArrayList<Asteroid>()
//
//    val nextSevenDaysFormattedDates = getNextSevenDaysFormattedDates()
//    for (formattedDate in nextSevenDaysFormattedDates) {
//        if (nearEarthObjectsJson.has(formattedDate)) {
//            val dateAsteroidJsonArray = nearEarthObjectsJson.getJSONArray(formattedDate)
//
//            for (i in 0 until dateAsteroidJsonArray.length()) {
//                val asteroidJson = dateAsteroidJsonArray.getJSONObject(i)
//                val id = asteroidJson.getLong("id")
//                val codename = asteroidJson.getString("name")
//                val absoluteMagnitude = asteroidJson.getDouble("absolute_magnitude_h")
//                val estimatedDiameter = asteroidJson.getJSONObject("estimated_diameter")
//                    .getJSONObject("kilometers").getDouble("estimated_diameter_max")
//
//                val closeApproachData = asteroidJson
//                    .getJSONArray("close_approach_data").getJSONObject(0)
//                val relativeVelocity = closeApproachData.getJSONObject("relative_velocity")
//                    .getDouble("kilometers_per_second")
//                val distanceFromEarth = closeApproachData.getJSONObject("miss_distance")
//                    .getDouble("astronomical")
//                val isPotentiallyHazardous = asteroidJson
//                    .getBoolean("is_potentially_hazardous_asteroid")
//
//                val asteroid = Asteroid(id, codename, formattedDate, absoluteMagnitude,
//                    estimatedDiameter, relativeVelocity, distanceFromEarth, isPotentiallyHazardous)
//                asteroidList.add(asteroid)
//            }
//        }
//    }
//
//    return asteroidList
//}
//
//private fun getNextSevenDaysFormattedDates(): ArrayList<String> {
//    val formattedDateList = ArrayList<String>()
//
//    val calendar = Calendar.getInstance()
//    for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
//        val currentTime = calendar.time
//        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
//        formattedDateList.add(dateFormat.format(currentTime))
//        calendar.add(Calendar.DAY_OF_YEAR, 1)
//    }
//
//    return formattedDateList

