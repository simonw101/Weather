package com.simonhanwilson.weather

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
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
import java.io.IOException
import java.util.*
import kotlin.math.roundToInt

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

            val url =  "http://api.openweathermap.org/data/2.5/weather?lat=${location?.latitude}&lon=${location?.longitude}&appid=d07c4e2ef774740b6bd8c2c43919b025"

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

                    var parsedData = ParseJson()

                    weather = parsedData.parseJsonData(result)

                    runOnUiThread {

                        tempTV.text = "${weather.temp.roundToInt()}°C"

                    }

                }

            }


        })

    }

}