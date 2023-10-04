package com.week_7_hw

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class InternalBroadcastReceiver : BroadcastReceiver() {
	override fun onReceive(context: Context?, intent: Intent?) {
		Toast.makeText(context, "Received internal broadcast", Toast.LENGTH_SHORT).show()
	}
}