package com.udacity.asteroidradar

import java.time.LocalDate

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    val CURRENTDATE = LocalDate.now().toString()
    val YESTERDAYDATE = LocalDate.now().minusDays(1).toString()
    const val APIKEY = "ZYylBzfTBo7ZrIOHItyqefWq9OdE7h2lQGUk476L"
}