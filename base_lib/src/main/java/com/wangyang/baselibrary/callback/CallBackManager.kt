package com.wangyang.baselibrary.callback

class CallBackManager {

    companion object {
        val INSTANCE: CallBackManager by lazy { CallBackManager() }
        private val CALL_BACK_MAP = HashMap<String, () -> Unit>()
        private val CALL_BACK_MAP_WITH_VALUE = HashMap<String, (msg: Any) -> Any>()
    }

    fun addCallBack(key: String, void: () -> Unit): CallBackManager {
        CALL_BACK_MAP[key] = void
        return this
    }

    fun addCallBackWithValue(key: String, void: (msg: Any) -> Any): CallBackManager {
        CALL_BACK_MAP_WITH_VALUE[key] = void
        return this
    }

    fun getCallBack(key: String): () -> Unit {
        return CALL_BACK_MAP[key] as () -> Unit
    }

    fun getCallBackWithValue(key: String): (msg: Any) -> Any {
        return CALL_BACK_MAP_WITH_VALUE[key] as (msg: Any) -> Any
    }

}