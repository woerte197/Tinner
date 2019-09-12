package com.example.webview_lib.webview.eventmanager

import java.util.HashMap

class WebEventManager {


    fun addEvent(key: String, event: Event): WebEventManager {
        EVENT_HASH_MAP[key] = event
        return this
    }

    fun getEvent(key: String): Event {
        val event = EVENT_HASH_MAP[key]
        return event ?: UndefineEvent()

    }

    companion object {

        private val EVENT_HASH_MAP = HashMap<String, Event>()

        val ins: WebEventManager by lazy { WebEventManager() }

    }


}
