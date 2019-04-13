package ru.tinkoff.arch.sample

import com.hannesdorfmann.mosby3.mvp.MvpView

interface LoginView : MvpView {

    fun onLoginResult(boolean: Boolean)

    fun onError(error: Throwable)
}
