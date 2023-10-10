package com.week_12_hw

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

class MyEventListener(private val context: Context) : SensorEventListener {
	override fun onSensorChanged(event: SensorEvent?) {
		if (event != null) {
			Toast.makeText(
				context,
				"Sensor: ${event.sensor.name}, Value: ${event.values[0]}",
				Toast.LENGTH_SHORT
			).show()
			println(event.sensor.name)
		} else {
			println("event is null")
		}
	}

	override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
		TODO("Not yet implemented")
	}
}