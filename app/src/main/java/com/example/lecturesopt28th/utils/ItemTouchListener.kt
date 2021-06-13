package com.example.lecturesopt28th.utils

@Deprecated("Use core Module")
interface ItemTouchListener {
    fun deleteItem(position: Int)
    fun moveItem(pos1: Int, pos2: Int)
}