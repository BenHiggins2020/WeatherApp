package com.ben.weatherapp.Data.Mappers

import android.util.Log
import com.ben.weatherapp.Data.WeatherData
import java.time.DayOfWeek
import java.time.LocalDateTime

private val DowMap:HashMap<Int,DayOfWeek> = HashMap()
private var DayList:MutableList<DayOfWeek> = mutableListOf()

fun createDayMap(time: LocalDateTime):HashMap<Int,DayOfWeek>{
    for (i in 0 ..6){
        DowMap[i] = time?.plusDays(i.toLong()).dayOfWeek
    }
    return DowMap
}

fun createDayList(time:LocalDateTime):List<DayOfWeek>{
    if(DowMap.isEmpty()){
        createDayMap(time = time)
    }
    Log.d("CreateDayList","${DowMap.values}")
    DayList = DowMap.values.toMutableList()
    return DayList
}

fun DayOfWeekToWeatherData(dayNumber:Int){


}

fun dayNumberToText(dayNumber:Int,time:LocalDateTime= LocalDateTime.now()): DayOfWeek? {
    if(DowMap.isEmpty()){
        createDayMap(time = time)
        Log.w("DayNumberToText","using custom time!!")
    }

    return DowMap.get(dayNumber)

}