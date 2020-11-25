package com.simonhanwilson.weather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    private var locationManager: LocationManager? = null

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_weather_icon.setIconSize(150)
        my_weather_icon.setIconColor(Color.WHITE)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as? LocationManager

        if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) !== PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)

        } else {

            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {

            1 -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if ((ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) === PackageManager.PERMISSION_GRANTED)) {

                        Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show()

                        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0F, locationListener)

                    } else {

                        Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()

                    }

                    return

                }

            }

        }

    }

    private val locationListener : LocationListener = object : LocationListener {

        override fun onLocationChanged(location: Location?) {

            getLocation(location)

//            val url =  "http://api.openweathermap.org/data/2.5/weather?lat=${location?.latitude}&lon=${location?.longitude}&units=metric&appid=d07c4e2ef774740b6bd8c2c43919b025"

            val url = "https://api.openweathermap.org/data/2.5/onecall?lat=${location?.latitude}&lon=${location?.longitude}&units=metric&exclude=hourly,minutely&appid=c6450fbf674a6265a015d0ca9c1edbc0"
            try {

                run(url)

            } catch (e: Exception) {

                e.printStackTrace()

            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            TODO("Not yet implemented")
        }

        override fun onProviderEnabled(provider: String?) {
            TODO("Not yet implemented")
        }

        override fun onProviderDisabled(provider: String?) {
            TODO("Not yet implemented")
        }

    }

    private fun getLocation(location: Location?) {

        val geoCoder = Geocoder(applicationContext, Locale.ENGLISH)

        try {

            if (location?.latitude != null && location?.longitude != null) {

                val address = geoCoder.getFromLocation(location.latitude, location.longitude, 1)

                if (address.size > 0) {

                    val fetchedAddress = address[0]

                    val strAddress = StringBuilder()

                    locationTV.text = strAddress.append(fetchedAddress.thoroughfare)

                    Log.i("address", fetchedAddress.thoroughfare)

                }

            }

        } catch (e: IOException) {

            e.printStackTrace()

        }

    }

    private fun run(url: String) {

       val request = Request.Builder()
           .url(url)
           .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {

                    val result = response.body!!.string()

                    var weather = CurrentWeatherModel()

                    val parsedData = ParseJson()

                    weather = parsedData.parseJsonData(result)

                    val jsonData = JSONObject(result)

                    val temp = jsonData.getJSONArray("daily").toString()

                    Log.i("info", temp)

                    runOnUiThread {

                        tempTV.text = String.format("%.0f", weather.temp) + "°C"

                        sunTV.text = "${weather.convertTimeSunrise()}/${weather.convertTimeSunset()}"

                        descTv.text = weather.description

                        when (weather.id) {
                            200 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            201 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            202 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            210 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            211 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            212 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            221 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            230 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            231 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            232 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_thunderstorm))


                            }
                            300 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            301 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            302 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            310 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            311 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            312 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            313 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            314 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            321 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_showers))


                            }
                            500 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            501 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            502 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            503 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            504 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            511 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            520 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            521 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            522 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            531 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_rain))


                            }
                            600 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            601 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            602 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            611 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            612 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            613 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            615 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            616 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            620 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            621 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            622 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_snow))


                            }
                            701 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_day_haze))


                            }
                            711 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_smoke))


                            }
                            721 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_day_haze))


                            }
                            731 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_dust))


                            }
                            741 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_fog))


                            }
                            751 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_sandstorm))


                            }
                            761 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_dust))


                            }
                            762 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_volcano))


                            }
                            771 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_windy))


                            }
                            781 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_tornado))


                            }
                            800 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_cloud))


                            }
                            801 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_cloud))


                            }
                            802 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_cloud))


                            }
                            803 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_cloud))


                            }
                            804 -> {

                                my_weather_icon.setIconResource(getString(R.string.wi_cloud))


                            }
                        }

                    }

                }

            }


        })

    }

}