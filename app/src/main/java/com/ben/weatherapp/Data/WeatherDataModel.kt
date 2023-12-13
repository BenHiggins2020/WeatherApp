package com.ben.weatherapp.Data

import com.squareup.moshi.Json

data class WeatherDataModel(
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures:List<Double>,
    @field:Json(name = "apparent_temperature")
    val temperatureFeels:List<Double>,
    @field:Json(name = "precipitation_probability")
    val precipitationProbabilities:List<Double>,
    @field:Json(name = "precipitation")
    val precipitations:List<Double>,
    @field:Json(name="weather_code")
    val weatherCodes:List<Int>

)
