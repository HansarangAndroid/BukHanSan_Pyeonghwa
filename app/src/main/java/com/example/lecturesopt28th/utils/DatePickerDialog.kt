package com.example.lecturesopt28th.utils

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.lecturesopt28th.databinding.DialogDatepickerBinding

class DatePickerDialog(val listener: DialogInterface): DialogFragment() {
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
        setPickerCycle()
        dialogDismiss()
    }

    override fun onResume() {
        getDeviceSize()
        super.onResume()
    }

    //defaultDisplay Deprecated로 인한 version 처리
    private fun getDeviceSize() {
        var deviceWidth = 0
        var deviceHeight = 0
        val outMetrics = DisplayMetrics()

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = requireActivity().display
            display?.getRealMetrics(outMetrics)
            deviceHeight = outMetrics.heightPixels
            deviceWidth = outMetrics.widthPixels
            setDialogSize(deviceWidth, deviceHeight)
        } else {
            @Suppress("DEPRECATION")
            val display = requireActivity().windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics)
            deviceHeight = outMetrics.heightPixels
            deviceWidth = outMetrics.widthPixels
            setDialogSize(deviceWidth, deviceHeight)
        }

    }

    private fun setDialogSize(deviceWidth: Int, deviceHeight:Int) {
        val params = dialog?.window?.attributes
        params?.width = (deviceWidth*0.8).toInt()
        params?.height = (deviceHeight*0.4).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    private fun setPickerCycle() {
        binding.apply {
            pickerYear.wrapSelectorWheel = false
            pickerMonth.wrapSelectorWheel = false
            pickerDay.wrapSelectorWheel = false
        }
    }

    private fun settingPicker() {
        setValues(binding.pickerYear, MIN_YEAR, MAX_YEAR)
        setValues(binding.pickerMonth, MIN_MONTH, MAX_MONTH)
        setValues(binding.pickerDay, MIN_DAY, MAX_DAY)
    }

    private fun setValues(picker: NumberPicker, min: Int, max: Int) {
        picker.apply {
            minValue = min
            maxValue = max
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

    companion object {
        private const val MAX_YEAR = 2021
        private const val MIN_YEAR = 1910
        private const val MAX_MONTH = 12
        private const val MIN_MONTH = 12
        private const val MAX_DAY = 31
        private const val MIN_DAY = 1
    }
}