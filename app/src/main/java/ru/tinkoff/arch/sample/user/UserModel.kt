package ru.tinkoff.arch.sample.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.tinkoff.arch.sample.misc.Either
import ru.tinkoff.arch.sample.misc.Left
import ru.tinkoff.arch.sample.misc.Right

class UserModel (private val userService: UserService) {

    // MutableLiveData имеет методы getValue, setValue, observe, observeForever
    // LiveData имеет методы getValue, observe, observeForever
    // наружу видно только LiveData и никто "снаружу" в других классах не сможет установить значение. Только получить.
    private val mutableLoginState = MutableLiveData<Either<Boolean, Throwable>>()
    val loginState : LiveData<Either<Boolean, Throwable>> = mutableLoginState

    fun signin(email: String, password: String) {
        userService.signin(SignInBody(email, password)).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                mutableLoginState.value = Right(t)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                mutableLoginState.value = Left(response.isSuccessful)
            }
        })
    }
}