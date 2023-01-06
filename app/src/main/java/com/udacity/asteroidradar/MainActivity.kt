package com.udacity.asteroidradar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
       Log.i("MainActivity", "HELLO")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
