package com.ben.weatherapp.Data.Repository

import android.util.Log
import com.ben.weatherapp.Api.WeatherApi
import com.ben.weatherapp.Data.DailyData
import com.ben.weatherapp.Data.Mappers.toDailyData
import com.ben.weatherapp.Domain.Util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<DailyData> {
        return try {
            Log.w("WeatherRepositoryImpl","getWeatherData - $lat $long")
            Resource.Success(
                data = api.getWeatherForecast(
                    lat = lat,
                    long = long
                ).toDailyData()
            )
        } catch (e:java.lang.Exception){
            e.printStackTrace()

            Log.w("WeatherRepositoryImpl","Resource Error during api call $e")
            Resource.Error(e.message)
        }
    }
}