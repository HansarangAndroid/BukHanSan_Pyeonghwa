package com.example.lecturesopt28th.utils

import androidx.recyclerview.widget.RecyclerView

object ItemDecorationRemover {
    fun <T: RecyclerView> T.removeItemDecorations(){
        while (itemDecorationCount>0){
            removeItemDecorationAt(0)
        }
    }
}