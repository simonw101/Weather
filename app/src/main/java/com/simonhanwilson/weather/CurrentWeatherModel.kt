package com.simonhanwilson.weather

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CurrentWeatherModel {

    var temp = 0.0

//        get() = field
//
//    set(value) {
//
//        field = (value - 273.15)
//
//    }

    var pressure = ""
    var humidity = ""
    var description = ""
    var id = 0
    var visibility = ""
    var windSpeed = ""
    var sunrise = ""

    fun convertTimeSunrise() : String {

        var time = this.sunrise.toLong()

        val sdf = SimpleDateFormat("HH:mm")

        val netDate = Date(time * 1000)

         return sdf.format(netDate)


    }

    var sunset = ""

    fun convertTimeSunset() : String {

        var time = this.sunset.toLong()

        val sdf = SimpleDateFormat("HH:mm")

        val netDate = Date(time * 1000)

        return sdf.format(netDate)


    }

}