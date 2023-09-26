package com.week_7_hw

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class W7BroadcastReceiver : BroadcastReceiver() {
	override fun onReceive(context: Context?, intent: Intent?) {
		val message: String = intent?.getStringExtra("message") ?: "No message received"
		println("Received message: $message from broadcast")
		val broadcastIntent: Intent = Intent(context, SubActivity::class.java)
		broadcastIntent.putExtra("MESSAGE_FROM_BROADCAST", message)
		context?.startActivity(broadcastIntent)
	}
}