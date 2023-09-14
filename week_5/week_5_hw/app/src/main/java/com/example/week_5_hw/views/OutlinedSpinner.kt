package com.example.week_5_hw.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

val options = listOf("Food", "Bill Payment", "Recharges", "Outing", "Other")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedSpinner(
	setChosenOption: (String) -> Unit = { },
) {
	val (expanded, setExpanded) = remember { mutableStateOf(false) }
	val (selectedOptionText, setSelectedOption) = remember { mutableStateOf(options[0]) }
	TextField(
		readOnly = true,
		enabled = false,
		value = selectedOptionText,
		onValueChange = { },
		label = { Text("Categories") },
		trailingIcon = {
			ExposedDropdownMenuDefaults.TrailingIcon(
				expanded = expanded,
			)
		},
		colors = ExposedDropdownMenuDefaults.textFieldColors(),
		modifier = Modifier
			.clickable(enabled = true, onClick = { setExpanded(true); println("clicked") })
			.fillMaxWidth()
	)
	DropdownMenu(
		expanded = expanded,
		onDismissRequest = { setExpanded(false); setChosenOption("") }
	) {
		options.map { option ->
			DropdownMenuItem(
				text = { Text(text = option) },
				onClick = {
					setChosenOption(option)
					setSelectedOption(option)
					setExpanded(false)
				}
			)
		}
	}
}