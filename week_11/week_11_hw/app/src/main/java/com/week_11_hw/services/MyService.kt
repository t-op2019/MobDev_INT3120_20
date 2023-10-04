package com.week_11_hw.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Process
import android.widget.Toast

class MyService : Service() {
	private var serviceLooper: Looper? = null
	private var serviceHandler: ServiceHandler? = null

	private inner class ServiceHandler(looper: Looper) : Handler(looper) {
		override fun handleMessage(msg: Message) {
			// Normally we would do some work here, like download a file.
			// For our sample, we just sleep for 5 seconds.
			try {
				Thread.sleep(5000)
			} catch (e: InterruptedException) {
				// Restore interrupt status.
				Thread.currentThread().interrupt()
			} finally {
				Toast.makeText(this@MyService, "Service done snoozing", Toast.LENGTH_SHORT).show()
			}

			// Stop the service using the startId, so that we don't stop
			// the service in the middle of handling another job
			stopSelf(msg.arg1)
		}
	}

	override fun onCreate() {
		HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
			start()
			serviceLooper = looper
			serviceHandler = ServiceHandler(looper)

		}

	}

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show()
		serviceHandler?.obtainMessage()?.also { msg ->
			msg.arg1 = startId
			serviceHandler?.sendMessage(msg)
		}
		return START_STICKY
	}

	override fun onBind(intent: Intent?): IBinder? {
		return null
	}

	override fun onDestroy() {
		Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show()
		super.onDestroy()
	}
}