package com.simonhanwilson.weather

import org.json.JSONObject

open class ParseJson {

    open fun parseJsonData(data: String) : CurrentWeatherModel {

        val currentWeather = CurrentWeatherModel()

        val jsonData = JSONObject(data)

        currentWeather.temp = jsonData.getJSONObject("current").getString("temp").toDouble()

        currentWeather.description = jsonData.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("description")

        currentWeather.id = jsonData.getJSONObject("current").getJSONArray("weather").getJSONObject(0).getString("id").toInt()

        currentWeather.humidity = jsonData.getJSONObject("current").getString("humidity")

        currentWeather.pressure = jsonData.getJSONObject("current").getString("pressure")

        currentWeather.visibility = jsonData.getJSONObject("current").getString("visibility")

        currentWeather.windSpeed = jsonData.getJSONObject("current").getString("wind_speed")

        currentWeather.sunrise = jsonData.getJSONObject("current").getString("sunrise")

        currentWeather.sunset = jsonData.getJSONObject("current").getString("sunset")

        return currentWeather

    }

}