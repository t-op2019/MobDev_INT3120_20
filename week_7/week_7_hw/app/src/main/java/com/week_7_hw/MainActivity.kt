package com.week_7_hw

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.week_7_hw.ui.theme.Week_7_hwTheme

class MainActivity : ComponentActivity() {
	private var feedback = ""
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Week_7_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Column {
						Greeting("Android", onClick = { startSubActivity() })
						if (feedback.isNotEmpty()) {
							Text(text = feedback)
						}
					}
				}
			}
		}
	}

	@Deprecated("Deprecated in Java")
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == RESULT_OK && requestCode == 69) {
			val feedback = data?.getStringExtra("FEEDBACK")
			this.feedback = feedback ?: "No feedback received"
		} else {
			this.feedback = "Error?!?!?!?!?!"
		}
	}

	private fun startSubActivity() {
		val intent: Intent = Intent(this, SubActivity::class.java)
		intent.putExtra("EXTRA_MESSAGE", "This is the message from the main activity")
		startActivityForResult(intent, 69)
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
	Column {
		Text(
			text = "Hello $name!, this is an explicit intent:",
			modifier = modifier
		)
		Button(onClick = { onClick() }) {
			Text(text = "Click me to go to the sub activity")
		}
	}
}