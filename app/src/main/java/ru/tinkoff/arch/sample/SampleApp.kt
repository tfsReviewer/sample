package ru.tinkoff.arch.sample

import android.app.Application
import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tinkoff.arch.sample.misc.DelayInterceptor
import ru.tinkoff.arch.sample.user.UserModel
import ru.tinkoff.arch.sample.user.UserService

class SampleApp : Application() {

    val model by lazy {
        createModel()
    }

    // сейчас у меня только одна модель и я весь код написал прямо в application. Если его будет много,
    // то надо будет переносить
    private fun createModel(): UserModel {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(DelayInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl("https://fintech.tinkoff.ru/api/")
            .build()

        return UserModel(retrofit.create(UserService::class.java))
    }
}

fun getModel(context: Context) = (context.applicationContext as SampleApp).model
