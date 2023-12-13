package com.ben.weatherapp.Data.Mappers

import android.util.Log
import com.ben.weatherapp.Data.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//map number of day to list of weather data
fun WeatherDataModel.toWeatherDataMap() : Map<Int, List<WeatherData>> {
    return time.mapIndexed{ index, time ->
        val temperature = temperatures[index]
        val temperatureFeel = temperatureFeels[index]
        val precipitationProbability = precipitationProbabilities[index]
        val precipitation = precipitations[index]
        val weatherCode = weatherCodes[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperature = temperature,
                temperatureFeel = temperatureFeel,
                precipitationProbability = precipitationProbability,
                precipitation = precipitation,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }


}

data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)


fun WeatherDataHolder.toDailyData():DailyData {
    Log.w("ToDailyData"," called")
    val weatherDataMap = weatherDataModel.toWeatherDataMap() //weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30 ) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return DailyData(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData)
}