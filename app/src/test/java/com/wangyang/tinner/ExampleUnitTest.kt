package com.wangyang.tinner

import org.junit.Test
import java.util.concurrent.Executors

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val fixedThreadPool = Executors.newFixedThreadPool(5)
        for (i in 0..9) {
            fixedThreadPool.execute {
                try {
                    println(i)
                    println(Thread.currentThread().name)
                } catch (e: InterruptedException) {
                    println(e)
                }
            }
        }
    }
}
