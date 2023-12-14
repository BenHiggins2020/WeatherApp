package com.ben.weatherapp.Presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ben.weatherapp.Data.Mappers.createDayList
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableOnSubscribe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.plugins.RxJavaPlugins.onSubscribe
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.time.DayOfWeek


private var showHide = true
fun setBoolean(){
    Log.w("setBoolean","showHide was $showHide")
    showHide = !showHide
    Log.w("setBoolean","showHide is now $showHide")
    getSubject().onNext(showHide)
}
fun getNewBoolean():Boolean{
    Log.d("getNewBoolean"," getNewBoolean - showHide = $showHide")
    return showHide
}
fun getSubject():Subject<Boolean>{
    val subject:Subject<Boolean> = BehaviorSubject.create()
    subject.subscribe { bool ->
        Log.d("Subject","subscribe for bool = $bool")

    }
    return subject
}


@Composable
fun DateOptionsButton(
    DayName:String,
    state: WeatherState
){
    state.dailyData?.weatherDataPerDay.let{  data ->
        val thing: List<DayOfWeek>
        var selectedDay:Int

        //weatherDataSubject.onNext(newDataForSelectedDay)
        if(data !=null ){
            thing = data[0]?.get(0)?.let {
                createDayList(it.time)
            }!!
        }
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            TextButton(
                onClick = {
                    setBoolean()
                    getNewBoolean()
                }
            ) {
                Text(
                    text = "$DayName",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
            if(getNewBoolean()){
                Log.w("getNewBoolean"," Showing Card!")
                Card(
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .height(60.dp)
                        .width(140.dp)

                ) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        content = {
                            items(7){dayNumber ->
                                DaysOfWeekList(
                                    dayNumber = dayNumber,
                                    modifier = Modifier
                                )

                            }
                        } )
                }
            }
        }

    }
}
