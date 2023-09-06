package com.example.playground

import android.os.Bundle
import android.widget.NumberPicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.playground.components.ActionButton
import com.example.playground.components.Donation
import com.example.playground.components.TopBar
import com.example.playground.ui.theme.Blue40
import com.example.playground.ui.theme.PlaygroundTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PlaygroundTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
				) {
					AppScaffold()
				}
			}
		}
	}
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
	val (selected, setSelected) = remember { mutableStateOf("PayPal") }
	val (sliderVal, setSliderVal) = remember { mutableStateOf(0) }
	val (amount, setAmount) = remember { mutableStateOf("") }
	val (donated, setDonated) = remember { mutableStateOf(0) }
	val paymentMethods = listOf("PayPal", "Direct")
	Scaffold(topBar = {
		TopBar()
	}, floatingActionButton = {
		ActionButton()
	}) { paddingValues ->
		Column(
			modifier = Modifier
				.padding(paddingValues)
				.padding(20.dp)
				.fillMaxWidth()
		) {
			Text(
				text = "Welcome Homer",
				fontSize = MaterialTheme.typography.titleLarge.fontSize,
				fontWeight = FontWeight(600),
			)

			Spacer(modifier = Modifier.height(20.dp))

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Column(Modifier.selectableGroup()) {
					Text(
						text = "Please Give Generously",
						fontSize = MaterialTheme.typography.titleMedium.fontSize,
						color = MaterialTheme.colorScheme.onBackground
					)
					// loop through paymentMethods and create a radio button for each
					paymentMethods.map {
						Row(
							horizontalArrangement = Arrangement.SpaceBetween,
							verticalAlignment = Alignment.CenterVertically,
						) {
							RadioButton(
								selected = selected == it,
								onClick = { setSelected(it) },
								colors = RadioButtonDefaults.colors(
									selectedColor = Blue40
								),
							)
							Text(text = it)
						}
					}
				}
				AndroidView(factory = { context ->
					NumberPicker(context).apply {
						value = sliderVal
						minValue = 0
						maxValue = 1000
						setOnValueChangedListener { _, _, newVal ->
							setSliderVal(newVal); setAmount(
							newVal.toString()
						)
						}
					}
				})
			}

			Spacer(modifier = Modifier.height(20.dp))

			LinearProgressIndicator(
				modifier = Modifier
					.fillMaxWidth(),
				progress = sliderVal.toFloat() / 1000f,
				color = Blue40
			)

			Spacer(modifier = Modifier.height(20.dp))

			Donation(amount, setAmount, donated, setDonated)
		}
	}

}