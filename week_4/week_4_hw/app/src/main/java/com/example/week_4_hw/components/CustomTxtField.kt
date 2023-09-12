package com.example.week_4_hw.components

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
/**
 * @param placeholder Set the placeholder text
 * @param keyboardType Use EditorInfo to set keyboard type
 * */
fun CustomTxtField(
	placeholder: String = "hint",
	keyboardType: Int = EditorInfo.TYPE_CLASS_TEXT
) {
	AndroidView(modifier = Modifier.fillMaxWidth(), factory = { context ->
		EditText(context).apply {
			hint = placeholder
			inputType = keyboardType
		}
	})
}