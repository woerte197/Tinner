package com.wangyang.baselibrary.utils

import android.content.Context
import android.content.SharedPreferences
import com.wangyang.baselibrary.BaseConstant
import com.wangyang.baselibrary.config.ConfigManager

class SharedPreferencesUtil {

    private val sharedPreferences: SharedPreferences = ConfigManager.getAppContext().getSharedPreferences(BaseConstant.BASE_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor? = null

    companion object {
        val INSTANCE = SharedPreferencesUtil()
    }

    init {
        editor = sharedPreferences.edit()
    }

    fun putBoolean(key: String, boolean: Boolean) {
        editor!!.putBoolean(key, boolean)
        editor!!.commit()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun putFloat(key: String, float: Float) {
        editor!!.putFloat(key, float)
        editor!!.commit()
    }

    fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    fun putInt(key: String, int: Int) {
        editor!!.putInt(key, int)
        editor!!.commit()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun putString(key: String, string: String) {
        editor!!.putString(key, string)
        editor!!.commit()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "")
    }

    fun putStringSet(key: String, values: Set<String>) {
        editor!!.putStringSet(key, values)
        editor!!.commit()
    }

    fun getStringSet(key: String): Set<String> {
        return sharedPreferences.getStringSet(key, null)
    }

    fun clear() {
        editor!!.clear()
        editor!!.commit()
    }

}