package com.week_7_hw

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val intent: Intent = this.intent
		val message: String? = intent.getStringExtra("EXTRA_MESSAGE")
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
						Text(text = message ?: "No message received")
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
						Button(onClick = { onBackPressed() }, modifier = spacerModifier) {
							Text(text = "Click me to go back to the main activity")
						}
					}
				}
			}
		}
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
}