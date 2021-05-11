package com.example.lecturesopt28th.utils

import android.widget.EditText
import androidx.lifecycle.LiveData

object InputChecker {
    fun checkBlank(listText: List<LiveData<String>>): Int {
        return listText.filter { it.value.isNullOrEmpty() }.count()
    }
}