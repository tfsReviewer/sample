package ru.tinkoff.arch.sample

import androidx.lifecycle.Observer
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import ru.tinkoff.arch.sample.misc.Left
import ru.tinkoff.arch.sample.misc.Right
import ru.tinkoff.arch.sample.user.SignInResult
import ru.tinkoff.arch.sample.user.UserModel

class LoginPresenter(private val userModel: UserModel) : MvpBasePresenter<LoginView>() {

    fun signIn(email: String, password: String) {
        val liveData = userModel.signin(email, password)
        liveData.observeForever(object : Observer<SignInResult> {
            override fun onChanged(state: SignInResult) {
                liveData.removeObserver(this)
                when (state) {
                    is Left -> ifViewAttached { view -> view.onLoginResult(state.value) }
                    is Right -> ifViewAttached { view -> view.onError(state.value) }
                }
            }
        })
    }
}
