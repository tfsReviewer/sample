package ru.tinkoff.arch.sample

import android.os.Bundle
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity<LoginView, LoginPresenter>(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            presenter.signIn(inputEmail.text.toString(), inputPswrd.text.toString())
        }
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter(getModel(this))
    }

    override fun onLoginResult(boolean: Boolean) {
        Toast.makeText(this, "Result $boolean", Toast.LENGTH_SHORT).show()
    }

    override fun onError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(this, "Smth wrong!", Toast.LENGTH_SHORT).show()
    }
}
