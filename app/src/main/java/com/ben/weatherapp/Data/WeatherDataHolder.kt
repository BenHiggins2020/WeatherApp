package com.ben.weatherapp.Data

import com.squareup.moshi.Json

data class WeatherDataHolder(
    @field:Json(name = "hourly")
    val weatherDataModel: WeatherDataModel
)
