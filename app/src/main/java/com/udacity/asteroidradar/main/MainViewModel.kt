package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    /**
     * Call getAsteroidProperties() on init so we can display status immediately.
     */
    init {
        Log.i("MainViewModel", "init")
        getAsteroidProperties()
    }

    /**
     * Sets the value of the response LiveData to the Asteroid API status or the successful number of
     * Asteroids retrieved.
     */
    private fun getAsteroidProperties() {
//        viewModelScope.launch {
//            try{
//                var listResult = AsteroidApi.retrofitService.getProperties()
//                _response.value = "Success: ${listResult} Picture properties retrieved"
//
//            } catch (e: Exception) {
//                _response.value = "Failure: ${e.message}"
//            }
//        }

        Log.i("MainViewModel", status.toString())
        AsteroidApi.retrofitService.getProperties().enqueue( object: Callback<PictureOfDay> {
            override fun onFailure(call: Call<PictureOfDay>, t: Throwable) {
                _status.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<PictureOfDay>, response: Response<PictureOfDay>) {
                _status.value = "Success: ${response} "


                val body = response.body()!!
                val url = body.url
                _pictureData.value = body
                Log.i("MainViewModel url", url)
            }
        })
    }
}