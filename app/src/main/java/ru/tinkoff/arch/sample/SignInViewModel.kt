package ru.tinkoff.arch.sample

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SignInViewModel(app: Application) : AndroidViewModel(app) {

    private val model = getModel(app)
    val loginState = model.loginState

    fun signIn(email: String, password: String) {
        model.signin(email, password)
    }
}
