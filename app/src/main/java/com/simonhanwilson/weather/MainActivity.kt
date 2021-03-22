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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var locationManager: LocationManager? = null

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

            val url = "https://api.openweathermap.org/data/2.5/onecall?lat=${location?.latitude}&lon=${location?.longitude}&units=metric&exclude=hourly,minutely&appid=c6450fbf674a6265a015d0ca9c1edbc0"
            try {

                run(url)

            } catch (e: Exception) {

                e.printStackTrace()

            }
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }

        override fun onProviderEnabled(provider: String?) {

        }

        override fun onProviderDisabled(provider: String?) {

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

                        getDate()

                        getTime()

                        when (weather.id) {
                            200 -> {

                               iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            201 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            202 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            210 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            211 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            212 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            221 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            230 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            231 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            232 -> {

                                iconImageView.setImageLevel(R.drawable.thunder)


                            }
                            300 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)


                            }
                            301 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)


                            }
                            302 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)


                            }
                            310 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)


                            }
                            311 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)


                            }
                            312 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)

                            }
                            313 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)


                            }
                            314 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)


                            }
                            321 -> {

                                iconImageView.setImageLevel(R.drawable.day_rain)


                            }
                            500 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            501 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            502 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            503 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            504 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            511 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            520 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            521 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            522 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            531 -> {

                                iconImageView.setImageLevel(R.drawable.rain)


                            }
                            600 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            601 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            602 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            611 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            612 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            613 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            615 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            616 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            620 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            621 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            622 -> {

                                iconImageView.setImageLevel(R.drawable.snow)


                            }
                            701 -> {

                                iconImageView.setImageLevel(R.drawable.mist)


                            }

                            741 -> {

                                iconImageView.setImageLevel(R.drawable.fog)


                            }

                            800 -> {

                                iconImageView.setImageLevel(R.drawable.day_clear)


                            }
                            801 -> {

                                iconImageView.setImageLevel(R.drawable.day_partial_cloud)


                            }
                            802 -> {

                                iconImageView.setImageLevel(R.drawable.day_partial_cloud)


                            }
                            803 -> {

                                iconImageView.setImageLevel(R.drawable.day_partial_cloud)


                            }
                            804 -> {

                                iconImageView.setImageLevel(R.drawable.day_partial_cloud)


                            }
                        }

                    }

                }

            }


        })

    }

    fun getDate() {

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("EEEE MMMM YYYY")
        val formatted = current.format(formatter)

        dateTV.text = "$formatted"

    }

    fun getTime() {

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val formatted = current.format(formatter)

        timeTV.text = "$formatted"

    }

}
