package com.wangyang.tinner.TEST
import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri


class StudentContentProvider : ContentProvider() {
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var mDataBase: SQLiteDatabase? = null
    var openHelper: DBOpenHelper? = null

    companion object {
        val AUTHORITY = "com.wangyang.mybasemodule.test.studentProvider"
        val STUDENT_URI_CODE = 0
        val URIMATCHER: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    init {
        URIMATCHER.addURI(AUTHORITY, "student", STUDENT_URI_CODE)
        openHelper = DBOpenHelper(context, null, null, 1, null)
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val uriType = URIMATCHER.match(uri)
        val row: Long

        when (uriType) {
            STUDENT_URI_CODE -> row = mDataBase!!.insert(DBOpenHelper.DATABASE_STUDENT_TABLE_NAME, null, values)
            else -> throw IllegalArgumentException("UnSupport Uri : $uri")
        }

        if (row > -1) {
            context.contentResolver.notifyChange(uri, null)
            return ContentUris.withAppendedId(uri, row)
        }

        return null
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val uriType = URIMATCHER.match(uri)
        val cursor: Cursor
        when (uriType) {
            STUDENT_URI_CODE -> cursor = mDataBase!!.query(DBOpenHelper.DATABASE_STUDENT_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder, null)
            else -> throw IllegalArgumentException("UnSupport Uri : $uri")
        }

        return cursor
    }

    override fun onCreate(): Boolean {
        mDataBase = openHelper?.writableDatabase
        return true
    }


    override fun getType(uri: Uri): String? {
        return null
    }
}