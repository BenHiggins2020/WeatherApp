package com.ben.weatherapp.Presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.DateTimeFormatter
import com.ben.weatherapp.R

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier:Modifier
){
    state.dailyData?.currentWeatherData?.let { data->


    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = backgroundColor,
        modifier = modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Today ${
                data.time.format(
                    DateTimeFormatter.ofPattern("HH:mm")
                )
            }",
            modifier = Modifier.align(Alignment.End),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = data.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${data.temperature} °F",
                fontSize = 50.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = data.weatherType.weatherDesc,
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                WeatherDataDisplay(
                    value = data.precipitation,
                    unit = " in",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_drop), // need to update this
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                )
                WeatherDataDisplay(
                    value = data.precipitationProbability,
                    unit = "%",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_rainshower), // need to update this
                    iconTint = Color.White,
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                )
                WeatherDataDisplay(
                    value = data.temperatureFeel,
                    unit = " °F",
                    icon = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), // need to update this
                    iconTint = Color.Transparent,
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier
                )
            }

        }
    }
    }
}