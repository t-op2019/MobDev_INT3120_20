package com.example.week_4_hw.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.week_4_hw.ui.theme.Blue40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar() {
	TopAppBar(
		title = { Text(text = "Essentials of Developing Android 5.0") },
		colors = TopAppBarDefaults.mediumTopAppBarColors(
			containerColor = Blue40,
			titleContentColor = Color.White,
		)
	)
}