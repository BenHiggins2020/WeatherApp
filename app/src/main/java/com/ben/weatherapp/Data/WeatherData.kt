package com.ben.weatherapp.Data

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperature: Double,
    val temperatureFeel: Double,
    val precipitationProbability: Double,
    val precipitation: Double,
    val weatherType: WeatherType
)
