package com.francisco.app_crud.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DbHelper(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private  const val DATABASE_VERSION = 1
        private  const val DATABASE_NAME = "vehicles.db"
        private  const val TABLE_GAMES = "vehicle"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_GAMES (id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT NOT NULL, model TEXT NOT NULL, velocity DOUBLE NOT NULL, km DOUBLE NOT NULL)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE $TABLE_GAMES")
        onCreate(p0)
    }

}