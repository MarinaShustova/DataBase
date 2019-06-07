package ru.nsu.fit.theater.base

import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.errror_screen.*
import ru.nsu.fit.theater.R

abstract class BaseActivity : AppCompatActivity() {
    fun showErrorScreen(errorMsg: String) {
        setContentView(R.layout.errror_screen)
        error_message.text = "Ошибка: $errorMsg"
    }
}