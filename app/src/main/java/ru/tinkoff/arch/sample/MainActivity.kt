package ru.tinkoff.arch.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import ru.tinkoff.arch.sample.misc.Either
import ru.tinkoff.arch.sample.misc.Left
import ru.tinkoff.arch.sample.misc.Right

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getModel(this).loginState.observe(this,
            Observer<Either<Boolean, Throwable>> {
                when (it) {
                    is Left -> onResult(it.value)
                    is Right -> onError(it.value)
                }
            })

        btnLogin.setOnClickListener {
            getModel(this).signin(inputEmail.text.toString(), inputPswrd.text.toString())
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
