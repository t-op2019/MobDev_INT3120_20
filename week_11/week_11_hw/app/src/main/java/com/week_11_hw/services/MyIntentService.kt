package com.week_11_hw.services

import android.app.IntentService
import android.content.Intent
import android.widget.Toast

class MyIntentService : IntentService("MyIntentService") {
	@Deprecated("Deprecated in Java", ReplaceWith("TODO(\"Not yet implemented\")"))
	override fun onHandleIntent(intent: Intent?) {
		try {
			Thread.sleep(5000)
		} catch (e: InterruptedException) {
			e.printStackTrace()
			Thread.currentThread().interrupt()
		} finally {
			Toast.makeText(this, "Intent Service has finished sleeping", Toast.LENGTH_LONG).show()
			stopSelf()
		}
	}
}