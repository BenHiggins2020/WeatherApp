package com.ben.weatherapp.Api

import com.ben.weatherapp.Data.WeatherDataHolder
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    /*

    https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,apparent_temperature,precipitation_probability,precipitation,weather_code&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch
        "v1/forecast?hourly=temperature_2m,apparent_temperature,precipitation_probability,precipitation,weather_code&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch"

    * */
    @GET(
    "/v1/forecast?hourly=temperature_2m,apparent_temperature,precipitation_probability,precipitation,weather_code&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch"
    )
    suspend fun getWeatherForecast(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
    ) : WeatherDataHolder



}