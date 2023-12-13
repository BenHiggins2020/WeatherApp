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

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier
){
    //NOTE WeatherDataPerDay.get(1) would be for tomorrow,
    // where get(0) is for today
    state.dailyData?.weatherDataPerDay?.get(0)?.let{  data ->

    Column(
            modifier = modifier
                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
                .padding(16.dp)


        ) {
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(content = {
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