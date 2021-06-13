package com.example.core

interface ItemTouchListener {
    fun deleteItem(position: Int)
    fun moveItem(pos1: Int, pos2: Int)
}