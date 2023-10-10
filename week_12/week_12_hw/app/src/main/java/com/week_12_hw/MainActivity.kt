package com.week_12_hw

import android.content.BroadcastReceiver
import android.content.Intent
import android.hardware.Sensor
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.week_12_hw.ui.theme.Week_12_hwTheme

class MainActivity : ComponentActivity() {
	private lateinit var manager: MyManager
	private lateinit var listener: MyEventListener

	@RequiresApi(Build.VERSION_CODES.S)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		manager = MyManager(this)
		listener = MyEventListener(this)
		val sensorType = Sensor.TYPE_AMBIENT_TEMPERATURE
		manager.registerListener(listener, sensorType)
		manager.registerTelephonyReceiver()
		manager.registerBluetoothDiscoveryReceiver()
		setContent {
			Week_12_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Column {
						Button(onClick = { checkMagneticFieldSensor() }) {
							Text(text = "Check Magnetic Field Sensor")
						}
						Button(onClick = { getWifiState() }) {
							Text(text = "Get Active Network Info")
						}
						Button(onClick = { dialPolice() }) {
							Text(text = "Call the police, but not for me!")
						}
						Button(onClick = { discoverDevices() }) {
							Text(text = "Discovery devices")
						}
					}
				}
			}
		}
	}

	override fun onStop() {
		super.onStop()
		manager.unregisterListener(listener)
		manager.unregisterTelephonyReceiver()
		manager.unregisterBluetoothDiscoveryReceiver()
	}

	private fun getWifiState() {
		val networkInfo = manager.getConnectivityManager().wifiState
		Toast.makeText(this, "Wifi state: $networkInfo", Toast.LENGTH_SHORT).show()
	}

	private fun checkMagneticFieldSensor() : Boolean {
		val isAvailable = manager.isMagneticFieldSensorAvailable()
		if (isAvailable) {
			Toast.makeText(this, "Magnetic Field Sensor is available", Toast.LENGTH_SHORT).show()
		}
		return isAvailable
	}

	private fun dialPolice() {
		val intent = Intent(Intent.ACTION_DIAL)
		intent.data = "tel:110".toUri()
		startActivity(intent)
	}

	@RequiresApi(Build.VERSION_CODES.S)
	private fun discoverDevices() {
		Toast.makeText(this, "Discovering...", Toast.LENGTH_SHORT).show()
		manager.startDiscovery()
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	Week_12_hwTheme {
		Greeting("Android")
	}
}