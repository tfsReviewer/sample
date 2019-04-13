package ru.tinkoff.arch.sample.user

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("signin")
    fun signin(@Body body: SignInBody) : Call<Void>
}

data class SignInBody(val email: String, val password: String)
