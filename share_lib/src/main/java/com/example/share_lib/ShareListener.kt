package com.example.share_lib

interface ShareListener {
        fun onComplete(platform: String?)
        fun onCancel(platform: String?)
        fun onError(platform: String?)
    }