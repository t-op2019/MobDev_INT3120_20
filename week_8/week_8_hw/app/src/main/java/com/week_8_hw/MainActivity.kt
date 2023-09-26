package com.week_8_hw

import android.content.ComponentName
import android.content.Intent
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.week_8_hw.ui.theme.Week_8_hwTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val spacerModifier: Modifier = Modifier.padding(10.dp)
		setContent {
			Week_8_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Column {
						Button(onClick = { broadcastIntent() }, modifier = spacerModifier) {
							Text(text = "Click me to broadcast an intent")
						}
						Button(onClick = { goToHW7() }, modifier = spacerModifier) {
							Text(text = "Click me to go to HW7")
						}
					}
				}
			}
		}
	}

	private fun broadcastIntent() {
		val intent: Intent = Intent("com.week_8_hw.CUSTOM_EVENT")
		intent.putExtra(
			"message",
			"This is a custom event broadcast from the main activity of week 8 hw"
		)
		sendBroadcast(intent)
	}

	private fun goToHW7() {
		val intent: Intent = Intent(Intent.ACTION_MAIN)
		intent.component = ComponentName("com.week_7_hw", "com.week_7_hw.SubActivity")
		startActivity(intent)
	}
}