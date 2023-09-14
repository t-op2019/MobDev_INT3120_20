package com.example.week_5_hw.views

import android.text.format.DateFormat
import android.widget.AnalogClock
import android.widget.DigitalClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Clock() {
	val dateState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
	val timeState = rememberTimePickerState()
	val (openDateDialog, setOpenDateDialog) = remember {
		mutableStateOf(false)
	}
	val (openTimeDialog, setOpenTimeDialog) = remember {
		mutableStateOf(false)
	}
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		AndroidView(factory = { context ->
			AnalogClock(context)
		})
		AndroidView(factory = { context ->
			DigitalClock(context)
		})
		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(50.dp)
		)
		Text(
			text = "Selected date: ${
				dateState.selectedDateMillis?.let {
					DateFormat.format(
						"dd/MM/yyyy",
						it
					)
				}
			}"
		)
		Text(
			text = "Selected time: ${
				timeState.hour
			}:${timeState.minute}"
		)
		Button(onClick = { setOpenDateDialog(true) }) {
			Text("Select Date")
		}
		Button(onClick = { setOpenTimeDialog(!openTimeDialog) }) {
			Text("${if (openTimeDialog) "Close" else "Open"} Timepicker")
		}
		if (openDateDialog) {
			DatePickerDialog(
				onDismissRequest = { setOpenDateDialog(false) },
				confirmButton = {
					Button(onClick = { setOpenDateDialog(false) })
					{ Text("Confirm") }
				}) {
				DatePicker(state = dateState)
			}
		}
		if (openTimeDialog) {
			TimePicker(state = timeState)
		}
	}
}