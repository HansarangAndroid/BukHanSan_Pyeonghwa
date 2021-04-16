package com.example.lecturesopt28th.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration(private val divWidth: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = divWidth
        outRect.right = divWidth
    }
}