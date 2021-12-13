package com.example.text_ceiba.data.repositories.common.local.tables

import android.content.Context
import com.example.text_ceiba.data.repositories.common.local.LocalSqlite
import com.google.gson.Gson
import com.google.gson.JsonElement
import org.json.JSONArray
import org.json.JSONObject

class UserSqlite(private val context: Context) {
    companion object{
        const val TABLE_NAME = "users"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_USERNAME = "username"
        fun create ():String {
            return "CREATE TABLE " + TABLE_NAME + " (" +
                    " $COLUMN_NAME INTEGER PRIMARY KEY," +
                    " $COLUMN_NAME TEXT," +
                    " $COLUMN_EMAIL TEXT," +
                    " $COLUMN_PHONE TEXT," +
                    " $COLUMN_USERNAME TEXT)"
        }
    }

    fun get(): String? {
        val dbHelper = LocalSqlite(context)
        try {
            val users = JSONArray()
            dbHelper.readableDatabase.rawQuery("SELECT * FROM ${UserSqlite.TABLE_NAME}", null).use {
                // index of the columns
                val idIndex = it.getColumnIndex(UserSqlite.COLUMN_NAME)
                val nameIndex = it.getColumnIndex(UserSqlite.COLUMN_NAME)
                val emailIndex = it.getColumnIndex(UserSqlite.COLUMN_NAME)
                val phoneIndex = it.getColumnIndex(UserSqlite.COLUMN_NAME)
                val username = it.getColumnIndex(UserSqlite.COLUMN_USERNAME)
                while (it.moveToNext()) {
                    val user = JSONObject();
                    user.put("id", it.getString(idIndex))
                        .put("name", it.getString(nameIndex))
                        .put("email", it.getString(emailIndex))
                        .put("phone", it.getString(phoneIndex))
                        .put("username", it.getString(username))
                    users.put(user);
                }
            }
            return Gson().toJson(users)
        }catch (e: Exception){
            return null
        }
    }
    fun get(userId: Int): String? {
        val dbHelper = LocalSqlite(context)
        try {
            val users = JSONArray()
            dbHelper.readableDatabase.rawQuery("SELECT * FROM ${UserSqlite.TABLE_NAME} WHERE id = $userId LIMIT 1",null).use {
                // index of the columns
                val idIndex = it.getColumnIndex(UserSqlite.COLUMN_NAME)
                val nameIndex = it.getColumnIndex(UserSqlite.COLUMN_NAME)
                val emailIndex = it.getColumnIndex(UserSqlite.COLUMN_NAME)
                val phoneIndex = it.getColumnIndex(UserSqlite.COLUMN_NAME)
                val username = it.getColumnIndex(UserSqlite.COLUMN_USERNAME)
                while (it.moveToNext()) {
                    val user = JSONObject();
                    user.put("id", it.getString(idIndex))
                        .put("name", it.getString(nameIndex))
                        .put("email", it.getString(emailIndex))
                        .put("phone", it.getString(phoneIndex))
                        .put("username", it.getString(username))
                    users.put(user);
                }
            }
            return Gson().toJson(users.get(0))
        }catch (e: Exception){
            return null
        }
    }

    fun insert(user: JSONObject): Boolean {
        val dbHelper = LocalSqlite(context)
        try {
            dbHelper.writableDatabase.execSQL("" +
                    "INSERT INTO ${UserSqlite.TABLE_NAME}" +
                    " (${UserSqlite.COLUMN_NAME}, ${UserSqlite.COLUMN_EMAIL}," +
                    " ${UserSqlite.COLUMN_PHONE}, ${UserSqlite.COLUMN_USERNAME})" +
                    " VALUES (?, ?, ?, ?)",
                arrayOf(user.getString("id"),
                    user.getString("name"),
                    user.getString("email"),
                    user.getString("phone"),
                    user.getString("username")))
            return true
        }catch (e: Exception){
            return false
        }
    }

}