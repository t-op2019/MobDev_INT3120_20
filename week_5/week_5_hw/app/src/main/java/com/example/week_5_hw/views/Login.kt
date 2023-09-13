package com.example.week_5_hw.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login() {
	val (username, setUsername) = remember { mutableStateOf("") }
	val (password, setPassword) = remember { mutableStateOf("") }
	Column(modifier = Modifier.fillMaxWidth()) {
		TextField(
			value = username,
			onValueChange = { setUsername(it) },
			singleLine = true,
			maxLines = 1,
			placeholder = { Text("Username") },
			modifier = Modifier.fillMaxWidth()
		)
		TextField(
			value = password,
			onValueChange = { setPassword(it) },
			singleLine = true,
			maxLines = 1,
			placeholder = { Text("Password") },
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
			modifier = Modifier.fillMaxWidth()
		)
		Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(10.dp)) {
			Text("Go!")
		}
	}
}