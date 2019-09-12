package com.wangyang.baselibrary.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.wangyang.baselibrary.config.ConfigManager

object PermissionUtil {

    fun isHasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(ConfigManager.getAppContext(), permission) == PackageManager.PERMISSION_GRANTED
    }

    fun isHasPermissions(permissions: Array<String>): Boolean {
        for (i in permissions) {
            if (!isHasPermission(i)) {
                return false
            }
        }
        return true
    }

    fun isHasPermission(result: Int): Boolean {
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(activity: Activity, permissions: Array<String>, permissionCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, permissionCode)
    }

    fun checkNoRequest(activity: Activity, permissions: Array<String>, permissionCode: Int) {
        if (!isHasPermissions(permissions)) {
            requestPermission(activity, permissions, permissionCode)
        }
    }
}