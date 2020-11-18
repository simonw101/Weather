package com.simonhanwilson.weather

import org.json.JSONObject

open class ParseJson {

    open fun parseJsonData(data: String) : CurrentWeatherModel {

        var currentWeather = CurrentWeatherModel()

        val jsonData = JSONObject(data)

        currentWeather.temp = jsonData.getJSONObject("main").getString("temp").toDouble()

        currentWeather.description = jsonData.getJSONArray("weather").getJSONObject(0).getString("description")

        currentWeather.humidity = jsonData.getJSONObject("main").getString("humidity")

        currentWeather.pressure = jsonData.getJSONObject("main").getString("pressure")

        currentWeather.visibility = jsonData.getString("visibility")

        currentWeather.windSpeed = jsonData.getJSONObject("wind").getString("speed")

        currentWeather.sunrise = jsonData.getJSONObject("sys").getString("sunrise")

        currentWeather.sunset = jsonData.getJSONObject("sys").getString("sunset")

        return currentWeather

    }

}