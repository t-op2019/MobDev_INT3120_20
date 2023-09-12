package com.example.week_4_hw.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CustomBtn(text: String = "CustomBtn") {
	Button(
		onClick = { /*TODO*/ },
		shape = RoundedCornerShape(5.dp),
		colors = ButtonDefaults.textButtonColors(
			containerColor = Color.LightGray,
			contentColor = Color.Black
		)
	) {
		Text(text = text)
	}
}