package com.week_11_hw.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import java.util.Random

/**
 * A service that is bound to an activity. Provides methods that returns a randomized number.
 * */
class MyBoundService : Service() {
	private val binder = LocalBinder()

	private val randomGenerator = Random()

	val getRandomNumber: Int
		get() = randomGenerator.nextInt(100)

	inner class LocalBinder : Binder() {
		// returns the instance of the service
		fun getService(): MyBoundService = this@MyBoundService
	}

	override fun onBind(intent: Intent?): IBinder {
		Toast.makeText(this, "Service has been bound", Toast.LENGTH_SHORT).show()
		return binder
	}

	override fun onUnbind(intent: Intent?): Boolean {
		Toast.makeText(this, "Service has been unbound", Toast.LENGTH_SHORT).show()
		return super.onUnbind(intent)
	}
}