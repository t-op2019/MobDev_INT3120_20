package com.week_14_hw

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.week_14_hw.ui.theme.Week_14_hwTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Week_14_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Greeting("Android")
				}
			}
		}
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	AndroidView(factory = { context ->
		WebView(context).apply {
			loadUrl("https://www.cornhub.website")
		}
	}, modifier = modifier)
//	Text(
//		text = "Hello $name!",
//		modifier = modifier
//	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	Week_14_hwTheme {
		Greeting("Android")
	}
}