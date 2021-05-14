package com.example.lecturesopt28th.utils

import android.util.Patterns.EMAIL_ADDRESS
import android.widget.EditText
import androidx.lifecycle.LiveData
import java.util.regex.Pattern

object InputChecker {
    fun checkBlank(textList: List<LiveData<String>>): Int {
        return textList.filter{ it.value.isNullOrBlank() }.count()
    }

    fun checkEmailPattern(email: String): Boolean {
        val emailPattern = Regex("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
        return email.matches(emailPattern)
    }
}