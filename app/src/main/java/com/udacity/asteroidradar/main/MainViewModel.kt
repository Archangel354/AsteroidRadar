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
    private val _asteroidLiveData = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val asteroidLiveData: LiveData<String>
        get() = _asteroidLiveData

    // add an encapsulated LiveData<PictureOfDay> pictureData
    private val _pictureData = MutableLiveData<PictureOfDay>()

    val pictureData: LiveData<PictureOfDay>
        get() = _pictureData

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    // add an encapsulated LiveData<Asteroid> pictureData
    private val _asteroidData = MutableLiveData<String>()

    val asteroidData: LiveData<String>
        get() = _asteroidData


    /**
     * Call getAsteroidProperties() on init so we can display asteroidLiveData immediately.
     */
    init {
        Log.i("MainViewModel", "init")
        getPictureProperties()
        getAsteroidProperties(CURRENTDATE, YESTERDAYDATE, APIKEY)
    }


    /**
     * Sets the value of the response LiveData to the Picture API asteroidLiveData
     */
    private fun getPictureProperties() {
        Log.i("MainViewModel Picture", asteroidLiveData.toString())
        PictureApi.PictureRetrofitService.getProperties().enqueue(object : Callback<PictureOfDay> {
            override fun onFailure(call: Call<PictureOfDay>, t: Throwable) {
                _asteroidLiveData.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<PictureOfDay>, response: Response<PictureOfDay>) {
                _asteroidLiveData.value = "Success: ${response} "
                val body = response.body()!!
                val url = body.url
                val title = body.title
                _pictureData.value = body
                Log.i("MainViewModel url", url)
            }
        })
    }

    /** * Sets the value of the response LiveData to the Asteroid API asteroidLiveData or the successful number of  * Asteroids retrieved.  */
//    private fun getAsteroidProperties() {
//        Log.i("MainViewModel Asteroid", asteroidLiveData.toString())
//        AsteroidApi.AsteroidRetrofitService.getProperties().enqueue(object : Callback<String> {
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.i("getAsteroid properties Failed",t.message.toString())
//                _asteroidLiveData.value = "Failure: " + t.message
//            }
//
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                Log.i("MainViewModel codename", response.toString())
//                val jsonObject = JSONObject(response.body()!!)
//                parseAsteroidsJsonResult(jsonObject)
//                _response.value = "Success: ${response}"
//                val body = response.body()!!
//            }
//    })
//}

        private fun getAsteroidProperties(startDate: String,endDate: String, apiKey: String) {
        viewModelScope.launch {
            try {
        Log.i("MainViewModel Asteroid", asteroidLiveData.toString())
                var listResult = AsteroidApi.AsteroidRetrofitService.getProperties(CURRENTDATE, YESTERDAYDATE, APIKEY)
                Log.i("MainViewModel size",  listResult.toString())

                if (listResult == null) {
                    //_asteroidData.value = listResult[0]
                    Log.i("MainViewModel listResult",  _asteroidData.value.toString())

                }
            } catch (e: Exception) {
                _asteroidLiveData.value = "Failure: ${e.message}"
            }
        }
    }

}