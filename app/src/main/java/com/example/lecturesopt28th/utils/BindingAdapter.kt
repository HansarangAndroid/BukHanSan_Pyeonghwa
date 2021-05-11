package com.example.lecturesopt28th

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseBindingMethod
import androidx.lifecycle.MutableLiveData
import coil.load
import com.bumptech.glide.Glide

@BindingAdapter("android:text")
fun setText(view:TextView, content: MutableLiveData<String>) {
    if (content == null) {
        view.text = ""
    } else {
        if(view.text != content.value) view.text = content.value
    }
}

@InverseBindingAdapter(attribute = "android:text", event = "textAttrChanged")
fun getTextString(view: EditText): String {
    return view.text.toString()
}

@BindingAdapter("textAttrChanged")
fun setTextWatcher(view: EditText, textAttrChanged: InverseBindingListener) {
    view.addTextChangedListener(object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            textAttrChanged?.onChange()
        }
    })
}

@BindingAdapter("uploadUrl")
fun uploadUrl(imageView:ImageView, url: String?) {
    if (url == null) {
        imageView.load(R.drawable.ic_baseline_person_24)
    } else {
        imageView.load(url)
    }
}

@InverseBindingAdapter(attribute = "android:checked", event = "android:checkedAttrchanged")
fun getIsChecked(switch: SwitchCompat): Boolean{
    return switch.isChecked
}

@BindingAdapter("android:checked")
fun setIsChecked(switch: SwitchCompat, isChecked: MutableLiveData<Boolean>){
    if (isChecked.value != switch.isChecked) switch.isChecked = isChecked.value!!

}

@BindingAdapter("android:checkedAttrchanged")
fun setCheckedChangeListener(switch: SwitchCompat, listener: InverseBindingListener){
    switch.setOnCheckedChangeListener { _ , _ -> listener.onChange() }
}

@BindingAdapter("clipToOutLine")
fun clipToOutLine(view:View, clipToOutLine: Boolean){
    view.clipToOutline = clipToOutLine
}

@BindingAdapter("setIsSelected")
fun setIsSelected(textView: TextView, isSelected: Boolean){
    textView.isSelected = isSelected

}