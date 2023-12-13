package com.ben.weatherapp.Data

data class DailyData(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
    )

