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
    // наружу видно только LiveData и никто "снаружи" в других классах не сможет установить значение. Только получить.
    fun signin(email: String, password: String) : LiveData<SignInResult> {
        val loginState = MutableLiveData<Either<Boolean, Throwable>>()
        userService.signin(SignInBody(email, password)).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                loginState.value = Right(t)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                loginState.value = Left(response.isSuccessful)
            }
        })
        return loginState
    }
}

// Чтобы не писать каждый раз Either<Boolean, Throwable>
typealias SignInResult = Either<Boolean, Throwable>
