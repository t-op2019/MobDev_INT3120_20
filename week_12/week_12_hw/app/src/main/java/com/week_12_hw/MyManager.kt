package com.week_12_hw

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Telephony
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService

class MyManager(private val context: Context) {
	private val sensorManager: SensorManager =
		getSystemService(context, SensorManager::class.java)!!

	private val wifiManager: WifiManager =
		getSystemService(context, WifiManager::class.java)!!

	private val telephonyReceiver = object : BroadcastReceiver() {
		override fun onReceive(context: Context?, intent: Intent?) {
			Telephony.Sms.Intents.getMessagesFromIntent(intent).forEach {
				Toast.makeText(context, it.displayMessageBody, Toast.LENGTH_SHORT).show()
			}
		}
	}

	private val bluetoothDiscoveryListener = object : BroadcastReceiver() {
		override fun onReceive(context: Context?, intent: Intent?) {
			println("Bluetooth discovery: $intent")
			if (intent?.action == BluetoothAdapter.ACTION_DISCOVERY_STARTED) {
				Toast.makeText(context, "Bluetooth discovery started", Toast.LENGTH_SHORT).show()
			} else if (intent?.action == BluetoothAdapter.ACTION_DISCOVERY_FINISHED) {
				Toast.makeText(context, "Bluetooth discovery finished", Toast.LENGTH_SHORT).show()
			} else if (intent?.action == BluetoothDevice.ACTION_FOUND) {
				println("Found bluetooth device: $intent")
			}
		}
	}

	private val bluetoothManager: BluetoothManager =
		getSystemService(context, BluetoothManager::class.java)!!
	private val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter

	fun getConnectivityManager(): WifiManager {
		return wifiManager
	}

	fun getSensorManager(): SensorManager {
		return sensorManager
	}

	fun getTelephonyReceiver(): BroadcastReceiver {
		return telephonyReceiver
	}

	@RequiresApi(Build.VERSION_CODES.S)
	fun startDiscovery() {
		if (ActivityCompat.checkSelfPermission(
				context,
				Manifest.permission.BLUETOOTH_SCAN
			) != PackageManager.PERMISSION_GRANTED
			|| ActivityCompat.checkSelfPermission(
				context,
				Manifest.permission.BLUETOOTH_CONNECT
			) != PackageManager.PERMISSION_GRANTED
		) {
			ActivityCompat.requestPermissions(
				context as Activity,
				arrayOf(Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT),
				0
			)
		}
		println("Is BT enabled: ${bluetoothAdapter?.isEnabled}")
		if (bluetoothAdapter?.isEnabled == false) {
			val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
			context.startActivity(enableBtIntent)
		}
		bluetoothAdapter?.startDiscovery()
	}

	fun isMagneticFieldSensorAvailable(): Boolean {
		return sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null
	}

	fun registerListener(listener: SensorEventListener, sensorType: Int) {
		sensorManager.registerListener(
			listener,
			sensorManager.getDefaultSensor(sensorType),
			SensorManager.SENSOR_DELAY_NORMAL
		)
	}

	fun registerTelephonyReceiver() {
		context.registerReceiver(
			telephonyReceiver,
			IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
		)
	}

	fun registerBluetoothDiscoveryReceiver() {
		context.registerReceiver(
			bluetoothDiscoveryListener,
			IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
		)
		context.registerReceiver(
			bluetoothDiscoveryListener,
			IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
		)
		context.registerReceiver(
			bluetoothDiscoveryListener,
			IntentFilter(BluetoothDevice.ACTION_FOUND)
		)
	}

	fun unregisterListener(listener: SensorEventListener) {
		sensorManager.unregisterListener(listener)
	}

	fun unregisterTelephonyReceiver() {
		context.unregisterReceiver(telephonyReceiver)
	}

	fun unregisterBluetoothDiscoveryReceiver() {
		context.unregisterReceiver(bluetoothDiscoveryListener)
	}
}