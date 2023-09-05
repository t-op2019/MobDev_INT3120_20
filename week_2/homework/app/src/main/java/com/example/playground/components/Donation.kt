package com.example.playground.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.playground.ui.theme.Blue40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Donation() {
	val (amount, setAmount) = remember { mutableStateOf("") }
	val (donated, setDonated) = remember { mutableStateOf(0) }
	Column {
		Row(
			modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top
		) {
			Text(text = "Amount", modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 0.dp))
			TextField(
				value = amount,
				onValueChange = { setAmount(it) },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
				colors = TextFieldDefaults.textFieldColors(
					containerColor = Color.Transparent,
					focusedIndicatorColor = Blue40,
					unfocusedIndicatorColor = Color.Black,
					cursorColor = Blue40,
					textColor = Color.Black,
				),
				modifier = Modifier
					.fillMaxWidth()
					.padding(0.dp)
			)
		}
		Spacer(modifier = Modifier.height(20.dp))
		Row(verticalAlignment = Alignment.Bottom) {
			ElevatedButton(
				onClick = {
					val amountInt = if (amount == "") 0 else amount.toInt()
					setDonated(donated + amountInt)
					setAmount("")
				},
				contentPadding = PaddingValues(10.dp),
				shape = RoundedCornerShape(5.dp),
				colors = ButtonDefaults.elevatedButtonColors(
					containerColor = Color.LightGray,
					contentColor = Color.Black,
				),
			) {
				Text(text = "DONATE")
			}
			Spacer(modifier = Modifier.width(30.dp))
			Text(text = "Total so far: $$donated")
		}
	}
}