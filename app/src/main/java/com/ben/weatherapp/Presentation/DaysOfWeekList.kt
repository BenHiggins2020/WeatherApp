package com.ben.weatherapp.Presentation

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ben.weatherapp.Data.Mappers.dayNumberToText

@Composable
fun DaysOfWeekList(
    dayNumber:Int,
    modifier: Modifier
){

    TextButton(
        onClick = {
            Log.d("DaysOfWeekList","Item clicked number = $dayNumber")
            //getData for the day number from weatherDataPerDay
        }
    ) {
        Text(
            text = "${dayNumberToText(dayNumber)}",
            textAlign = TextAlign.Center,
            fontSize = 18.sp

        )
    }

}