package com.example.lecturesopt28th.utils

import android.widget.EditText
import androidx.lifecycle.LiveData

object InputChecker {
    fun checkBlank(textList: List<LiveData<String>>): Int {
        return textList.filter{ it.value.isNullOrBlank() }.count()
    }
}