package com.wangyang.tinner.TEST

import java.util.concurrent.Executors

private val pool by lazy {
    Executors.newCachedThreadPool()
}

class AsyncTask (private val block:()->Unit){
    fun excute() = pool.execute(block)
}