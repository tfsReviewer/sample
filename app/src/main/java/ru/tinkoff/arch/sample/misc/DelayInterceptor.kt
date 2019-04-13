package ru.tinkoff.arch.sample.misc

import okhttp3.Interceptor
import okhttp3.Response

class DelayInterceptor(private val delay: Long = 5_000) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Thread.sleep(delay)
        return chain.proceed(chain.request())
    }
}
