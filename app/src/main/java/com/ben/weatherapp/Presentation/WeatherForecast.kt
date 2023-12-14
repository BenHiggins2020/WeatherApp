package com.ben.weatherapp.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ben.weatherapp.Data.WeatherData
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier
){
    //NOTE WeatherDataPerDay.get(1) would be for tomorrow,
    // where get(0) is for today
    state.dailyData?.weatherDataPerDay?.get(0)?.let{  data ->

        var appropriateWeatherData:WeatherData
        val weatherDataSubject: Subject<WeatherData> = BehaviorSubject.create()
        weatherDataSubject.subscribe{updatedData -> appropriateWeatherData = updatedData}
    Column(
            modifier = modifier
                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
                .padding(16.dp)


        ) {
            DateOptionsButton(DayName = "Today",state)
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(content = {
//                items(appropriateWeatherData) { item: WeatherData ->

                items(data) { item: WeatherData ->
                  HourlyWeatherDisplay(item,
                  modifier = Modifier
                      .height(100.dp)
                      .padding(horizontal = 16.dp)
                  )
              }
            })
        }
    }

}