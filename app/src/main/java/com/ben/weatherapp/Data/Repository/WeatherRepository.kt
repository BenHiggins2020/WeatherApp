package com.ben.weatherapp.Data.Repository

import com.ben.weatherapp.Data.DailyData
import com.ben.weatherapp.Domain.Util.Resource

interface WeatherRepository {

    suspend fun getWeatherData(lat:Double, long:Double): Resource<DailyData>
}