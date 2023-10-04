package com.week_7_hw

import android.app.SearchManager
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.week_7_hw.ui.theme.Week_7_hwTheme

class SubActivity : ComponentActivity() {
	private val receiver: InternalBroadcastReceiver = InternalBroadcastReceiver()
	private val intentFilter: IntentFilter = IntentFilter("INTERNAL_BROADCAST")

	@RequiresApi(Build.VERSION_CODES.O)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		registerReceiver(receiver, intentFilter, RECEIVER_EXPORTED)
		val intent: Intent = this.intent
		val message: String? = intent.getStringExtra("EXTRA_MESSAGE")
		val broadcastMessage: String? = intent.getStringExtra("MESSAGE_FROM_BROADCAST")
		val spacerModifier: Modifier = Modifier.padding(10.dp)
		setContent {
			Week_7_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Column {
						Text(text = "This is the sub activity")
						if (!message.isNullOrEmpty()) {
							Text(text = "Message from main activity: $message")
						}
						if (!broadcastMessage.isNullOrEmpty()) {
							Text(text = "Message from broadcast: $broadcastMessage")
						}
						Button(onClick = { dialPolice() }, modifier = spacerModifier) {
							Text(text = "Click me to dial the police")
						}
						Button(onClick = { searchStuff() }, modifier = spacerModifier) {
							Text(text = "Click me to search some stuff")
						}
						Button(onClick = { sendMsgToUrMom() }, modifier = spacerModifier) {
							Text(text = "Click me to do send msg to your mom")
						}
						Button(onClick = { showPics() }, modifier = spacerModifier) {
							Text(text = "Click me to show pics")
						}
						Button(onClick = { showContacts() }, modifier = spacerModifier) {
							Text(text = "Click me to open contacts")
						}
						Button(onClick = { showLocation() }, modifier = spacerModifier) {
							Text(text = "Click me to show a random location")
						}
						Button(onClick = { goToHW6() }, modifier = spacerModifier) {
							Text(text = "Click me to go to HW6")
						}
						Button(onClick = { goToHW8() }, modifier = spacerModifier) {
							Text(text = "Click me to go to HW8")
						}
						Button(onClick = { broadcastInternally() }, modifier = spacerModifier) {
							Text(text = "Click me to broadcast internally")
						}
						Button(onClick = { onBackPressed() }, modifier = spacerModifier) {
							Text(text = "Click me to go back to the main activity")
						}
					}
				}
			}
		}
	}

	override fun onStop() {
		super.onStop()
		unregisterReceiver(receiver)
	}

	override fun finish() {
		val intent: Intent = Intent()
		intent.putExtra("FEEDBACK", "This is the feedback from the sub activity")
		setResult(RESULT_OK, intent)
		super.finish()
	}

	private fun dialPolice() {
		val intent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:911"))
		startActivity(intent)
	}

	private fun searchStuff() {
		val intent: Intent = Intent(Intent.ACTION_WEB_SEARCH)
		intent.putExtra(SearchManager.QUERY, "why Elden Ring is the greatest game ever")
		startActivity(intent)
	}

	private fun sendMsgToUrMom() {
		val intent: Intent = Intent(Intent.ACTION_SENDTO, Uri.parse("sms:69420"))
		intent.putExtra("sms_body", "Hey lil momma, let me whisper in your ear")
		startActivity(intent)
	}

	private fun showPics() {
		val intent: Intent = Intent()
		intent.type = "image/pictures/*"
		intent.action = Intent.ACTION_GET_CONTENT
		startActivity(intent)
	}

	private fun showContacts() {
		val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"))
		startActivity(intent)
	}

	private fun showLocation() {
		val geoCode = "geo:420.69, 69.420"
		val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoCode))
		startActivity(intent)
	}

	private fun goToHW6() {
		val intent: Intent = Intent(Intent.ACTION_MAIN)
		intent.component = ComponentName("com.week_6_hw", "com.week_6_hw.MainActivity")
		startActivity(intent)
	}

	private fun goToHW8() {
		val intent: Intent = Intent(Intent.ACTION_MAIN)
		intent.component = ComponentName("com.week_8_hw", "com.week_8_hw.MainActivity")
		startActivity(intent)
	}

	private fun broadcastInternally() {
		val intent: Intent = Intent("INTERNAL_BROADCAST")
		intent.putExtra(
			"message",
			"This is an internal broadcast"
		)
		sendBroadcast(intent)
	}
}