package com.simonhanwilson.weather

class CurrentWeatherModel {

    var temp = 0.0

        get() = field

    set(value) {

        field = (value - 273.15)

    }

    var pressure = ""
    var humidity = ""
    var description = ""
    var visibility = ""
    var windSpeed = ""
    var sunrise = ""
    var sunset = ""

}