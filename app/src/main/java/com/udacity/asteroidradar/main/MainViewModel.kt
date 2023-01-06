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
import timber.log.Timber

class MainViewModel : ViewModel() {

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
        getAsteroidProperties()
    }

    /**
     * Sets the value of the response LiveData to the Asteroid API status or the successful number of
     * Asteroids retrieved.
     */
    private fun getAsteroidProperties() {
        Log.i("MainViewModel", response.toString())
        AsteroidApi.retrofitService.getProperties()?.enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }
        })
    }
}