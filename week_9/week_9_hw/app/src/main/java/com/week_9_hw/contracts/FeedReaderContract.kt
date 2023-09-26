package com.week_9_hw.contracts

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object FeedReaderContract {
	// inner class that defines the table layout
	object FeedEntry : BaseColumns {
		const val TABLE_NAME = "entry"
		const val COL_NAME_TITLE = "title"
		const val COL_NAME_SUBTITLE = "subtitle"
	}

	// SQL statements for creating and deleting the table
	private const val SQL_CREATE_ENTRIES =
		"CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
				"${BaseColumns._ID} INTEGER PRIMARY KEY," +
				"${FeedEntry.COL_NAME_TITLE} TEXT," +
				"${FeedEntry.COL_NAME_SUBTITLE} TEXT)"

	private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"

	class FeedReaderDbHelper(context: Context) :
		SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
		// static members
		companion object {
			const val DATABASE_NAME = "FeedReader.db"
			const val DATABASE_VERSION = 1
		}

		override fun onCreate(db: SQLiteDatabase?) {
			db?.execSQL(SQL_CREATE_ENTRIES)
		}

		override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
			// This database is only a cache for online data, so its upgrade policy is
			// to simply discard the data and start over
			db?.execSQL(SQL_DELETE_ENTRIES)
			onCreate(db)
		}
	}
}