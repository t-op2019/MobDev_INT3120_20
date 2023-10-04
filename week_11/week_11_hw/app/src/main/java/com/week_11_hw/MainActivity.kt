package com.week_11_hw

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.week_11_hw.services.MyService
import com.week_11_hw.ui.theme.Week_11_hwTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Week_11_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Column {
						Button(onClick = { startMyService() }) {
							Text(text = "Start a Service")
						}
						Spacer(modifier = Modifier.height(10.dp))
						Button(onClick = { goToBoundActivity() }) {
							Text(text = "Start Bound Activity")
						}
					}
				}
			}
		}
	}

	private fun startMyService() {
		val serviceIntent = Intent(this, MyService::class.java)
		startService(serviceIntent)
	}

	private fun goToBoundActivity() {
		val intent = Intent(this, BoundActivity::class.java)
		startActivity(intent)
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	Week_11_hwTheme {
		Greeting("Android")
	}
}