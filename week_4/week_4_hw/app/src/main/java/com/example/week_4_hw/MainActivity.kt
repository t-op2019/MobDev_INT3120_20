package com.example.week_4_hw

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week_4_hw.components.AppTopBar
import com.example.week_4_hw.components.BottomNav
import com.example.week_4_hw.components.CustomTxtField
import com.example.week_4_hw.components.RadioOption
import com.example.week_4_hw.ui.theme.Week_4_hwTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Week_4_hwTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					AppScaffold()
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AppScaffold() {
	val cheeses = listOf("Cheese", "2 x Cheese", "None")
	val shapes = listOf("Square", "Round")
	val (selectedCheese, setSelectedCheese) = remember {
		mutableStateOf("Cheese")
	}
	val (selectedShape, setSelectedShape) = remember {
		mutableStateOf("Square")
	}
	Scaffold(
		topBar = { AppTopBar() },
	) {
		Column(
			modifier = Modifier
				.padding(it)
				.padding(20.dp)
		) {
			CustomTxtField(placeholder = "Enter your Name")
			CustomTxtField(
				placeholder = "Enter your phone number",
				EditorInfo.TYPE_CLASS_NUMBER
			)

			Row(modifier = Modifier.selectableGroup()) {
				cheeses.map { cheese ->
					RadioOption(
						onClick = { setSelectedCheese(cheese) },
						text = cheese,
						selected = selectedCheese == cheese
					)
				}
			}
			Row(modifier = Modifier.selectableGroup()) {
				shapes.map { shape ->
					RadioOption(
						onClick = { setSelectedShape(shape) },
						text = shape,
						selected = selectedShape == shape
					)
				}
			}

			BottomNav()
		}
	}
}