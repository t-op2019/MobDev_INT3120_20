package com.week_9_hw

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.week_9_hw.contracts.FeedReaderContract
import com.week_9_hw.contracts.FeedReaderContract.FeedEntry
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DBCompose(context: Context) {
	val (search, setSearch) = remember { mutableStateOf("") }
	val (title, setTitle) = remember { mutableStateOf("hello") }
	val (subtitle, setSubtitle) = remember { mutableStateOf("world") }
	val (searchRes, setSearchRes) = remember { mutableStateOf("") }
	val dbHelper = FeedReaderContract.FeedReaderDbHelper(context)

	fun onClickRead(query: String) {
		if (query.isNotEmpty()) {
			val results = readDBByTitle(dbHelper, query)
			if (results.isEmpty()) {
				setSearchRes("No results found")
			} else {
				setSearchRes(results.joinToString("\n"))
			}
		}
	}

	fun onClickWrite(newTitle: String, newSubtitle: String) {
		if (newTitle.isNotEmpty() && newSubtitle.isNotEmpty()) {
			writeToDB(dbHelper, newTitle, newSubtitle)
		}
	}

	fun clearSearch() {
		setSearch("")
		setSearchRes("")
	}

	val spacerModifier = Modifier.padding(vertical = 5.dp)
	Column {
		OutlinedTextField(
			value = search,
			onValueChange = { setSearch(it) },
			label = { Text("Search SQLite by title") },
			modifier = spacerModifier
		)
		OutlinedTextField(
			value = title,
			onValueChange = { setTitle(it) },
			label = { Text("Add title to SQLite") },
			modifier = spacerModifier
		)
		OutlinedTextField(
			value = subtitle,
			onValueChange = { setSubtitle(it) },
			label = { Text("Add subtitle to SQLite") },
			modifier = spacerModifier
		)

		if (searchRes.isNotEmpty()) {
			Text(text = "Search results: \n $searchRes", modifier = spacerModifier)
		}

		CustomBtn(
			text = "Read SQLite",
			onClick = { onClickRead(search) },
		)
		CustomBtn(
			text = "Write to SQLite",
			onClick = { onClickWrite(title, subtitle) },
		)
		CustomBtn(text = "Clear search", onClick = ::clearSearch)
		CustomBtn(text = "Clear all") {
			clearAllDB(dbHelper)
		}
	}
}

private fun readDBByTitle(dbHelper: SQLiteOpenHelper, title: String): Array<String> {
	val readableDB = dbHelper.readableDatabase
	val projection = arrayOf(BaseColumns._ID, FeedEntry.COL_NAME_TITLE, FeedEntry.COL_NAME_SUBTITLE)
	val selection = "${FeedEntry.COL_NAME_TITLE} = ?"
	val selectionArgs = arrayOf(title)

	val cursor = readableDB.query(
		FeedEntry.TABLE_NAME,
		projection,
		selection,
		selectionArgs,
		null,
		null,
		null
	)

	val results = mutableListOf<String>()

	with(cursor) {
		while (moveToNext()) {
			val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
			val itemTitle = getString(getColumnIndexOrThrow(FeedEntry.COL_NAME_TITLE))
			val itemSubtitle = getString(getColumnIndexOrThrow(FeedEntry.COL_NAME_SUBTITLE))
			results.add("item id: $itemId, item title: $itemTitle, item subtitle: $itemSubtitle")
		}
	}
	cursor.close()
	return results.toTypedArray()
}

private fun writeToDB(dbHelper: SQLiteOpenHelper, title: String, subtitle: String) {
	val writableDB = dbHelper.writableDatabase
	// Create a new map of values, where column names are the keys
	val values = ContentValues().apply {
		put(FeedEntry.COL_NAME_TITLE, title)
		put(FeedEntry.COL_NAME_SUBTITLE, subtitle)
	}
	val newRowId = writableDB.insert(FeedEntry.TABLE_NAME, null, values)
	println("new row id: $newRowId")
}

private fun clearAllDB(dbHelper: SQLiteOpenHelper) {
	val writableDB = dbHelper.writableDatabase
	writableDB.delete(FeedEntry.TABLE_NAME, null, null)
}