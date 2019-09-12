package com.wangyang.tinner.TEST
import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int, errorHandler: DatabaseErrorHandler?) : SQLiteOpenHelper(context, name, factory, version, errorHandler) {

    companion object {
        val DATABASE_NAME = "com_android_peter_provider.db"
        val DATABASE_STUDENT_TABLE_NAME = "student"
        val DATABASE_VERSION = 1
        val CREATE_STUDENT_TABLE = ("CREATE TABLE IF NOT EXISTS "
                + DATABASE_STUDENT_TABLE_NAME
                + "(id INTEGER PRIMARY KEY,"
                + "name TEXT VARCHAR(20) NOT NULL,"
                + "gender BIT DEFAULT(1),"
                + "number TEXT VARCHAR(12) NOT NULL,"
                + "score INTEGER CHECK(score >= 0 and score <= 100))")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_STUDENT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}