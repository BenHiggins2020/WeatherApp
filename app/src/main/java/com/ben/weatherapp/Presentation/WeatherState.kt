package com.ben.weatherapp.Presentation

import com.ben.weatherapp.Data.DailyData

data class WeatherState(
    val dailyData: DailyData? = null,
    val isLoading: Boolean = false,
    val error: String? = null

)
