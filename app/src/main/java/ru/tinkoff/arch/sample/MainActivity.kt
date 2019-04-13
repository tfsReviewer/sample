package ru.tinkoff.arch.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import ru.tinkoff.arch.sample.misc.Left
import ru.tinkoff.arch.sample.misc.Right
import ru.tinkoff.arch.sample.user.SignInResult

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)

        // в observe передали LifecycleOwner и обсервер будет отписан при onDestroy
        // про LiveData можно почитать тут: https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/525-urok-2-livedata.html
        viewModel.loginState.observe(this, Observer<SignInResult> {
            when (it) {
                is Left -> onResult(it.value)
                is Right -> onError(it.value)
            }
        })

        btnLogin.setOnClickListener {
            viewModel.signIn(inputEmail.text.toString(), inputPswrd.text.toString())
        }
    }

    private fun onResult(boolean: Boolean) {
        Toast.makeText(this, "Result $boolean", Toast.LENGTH_SHORT).show()
    }

    private fun onError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(this, "Smth wrong!", Toast.LENGTH_SHORT).show()
    }
}
