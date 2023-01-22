package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants.APIKEY
import com.udacity.asteroidradar.Constants.CURRENTDATE
import com.udacity.asteroidradar.Constants.YESTERDAYDATE
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.PictureApi
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class MainViewModel : ViewModel() {


    // The internal MutableLiveData String that stores the most recent response
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val status: LiveData<String>
        get() = _status

    // add an encapsulated LiveData<PictureOfDay> pictureData
    private val _pictureData = MutableLiveData<PictureOfDay>()

    val pictureData: LiveData<PictureOfDay>
        get() = _pictureData

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getAsteroidProperties() on init so we can display status immediately.
     */
    init {
        Log.i("MainViewModel", "init")
        getPictureProperties()
        getAsteroidProperties()
    }


    /**
     * Sets the value of the response LiveData to the Picture API status
     */
    private fun getPictureProperties() {
        Log.i("MainViewModel", status.toString())
        PictureApi.PictureRetrofitService.getProperties().enqueue(object : Callback<PictureOfDay> {
            override fun onFailure(call: Call<PictureOfDay>, t: Throwable) {
                _status.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<PictureOfDay>, response: Response<PictureOfDay>) {
                _status.value = "Success: ${response} "


                val body = response.body()!!
                val url = body.url
                val title = body.title
                _pictureData.value = body
                Log.i("MainViewModel url", url)
            }
        })
    }

    /**
     * Sets the value of the response LiveData to the Asteroid API status or the successful number of
     * Asteroids retrieved.
     */
    private fun getAsteroidProperties() {
        Log.i("MainViewModel", "getAsteroidProperties")
//        AsteroidApi.AsteroidRetrofitService.getProperties(YESTERDAYDATE,CURRENTDATE, APIKEY).enqueue(object : Callback<Asteroid> {
//            override fun onFailure(call: Call<Asteroid>, t: Throwable) {
//                _response.value = "Failure: " + t.message
        viewModelScope.launch {
            try {
                var asteroidList = AsteroidApi.AsteroidRetrofitService.getProperties()
                   if (asteroidList.size > 0)
                       _response.value = asteroidList[0].toString()

            } catch (e: Exception) {
                Log.d("Failed Asteroids", "Error: ${e.localizedMessage}")
            }
        }
    }
}