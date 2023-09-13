package com.example.week_5_hw.views

import android.widget.AnalogClock
import android.widget.DigitalClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Clock() {
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
	}
}