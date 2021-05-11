package com.example.lecturesopt28th.utils

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.lecturesopt28th.databinding.DialogDatepickerBinding

class CustomDialog(val listener: DialogInterface): DialogFragment() {
    private var _binding: DialogDatepickerBinding? = null
    private val binding get() = _binding ?: throw error("dialog fragment error")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogDatepickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingPicker()
        settingValue()
        dialogDismiss()
    }

    override fun onResume() {
        getDeviceSize()
        super.onResume()
    }

    private fun getDeviceSize() {
        val outMetrics = DisplayMetrics()
        var width = 0
        var height = 0
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = requireActivity().display
            display?.getRealMetrics(outMetrics)
            height = outMetrics.heightPixels
            width = outMetrics.widthPixels

        } else {
            @Suppress("DEPRECATION")
            val display = requireActivity().windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics)

            height = outMetrics.heightPixels
            width = outMetrics.widthPixels
        }

        val params = dialog?.window?.attributes
        params?.width = (width*0.8).toInt()
        params?.height = (height*0.4).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    private fun settingPicker() {
        binding.apply {
            pickerYear.wrapSelectorWheel = false
            pickerMonth.wrapSelectorWheel = false
            pickerDay.wrapSelectorWheel = false
        }
    }

    private fun settingValue() {
        binding.pickerYear.apply {
            minValue = 1910
            maxValue = 2021
            value = 1990
        }
        binding.pickerMonth.apply {
            minValue = 1
            maxValue = 12
        }

        binding.pickerDay.apply {
            minValue = 1
            maxValue = 31
        }
    }

    private fun dialogDismiss() {
        binding.buttonComplete.setOnClickListener {
            val year = binding.pickerYear.value.toString()
            val month = binding.pickerMonth.value.toString()
            val day = binding.pickerDay.value.toString()
            listener?.applyDate(year, month, day)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}