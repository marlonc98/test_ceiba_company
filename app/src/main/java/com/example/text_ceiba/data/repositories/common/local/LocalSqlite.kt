package com.example.text_ceiba.data.repositories.common.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.text_ceiba.data.repositories.common.local.tables.UserSqlite

class LocalSqlite(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "text_ceiba.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(UserSqlite.create())
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, p1: Int, p2: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${UserSqlite.TABLE_NAME}")
        onCreate(sqLiteDatabase)
    }
}