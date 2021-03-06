package com.example.lecturesopt28th.utils

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.lecturesopt28th.databinding.DialogDatepickerBinding

//Fixme: GithubReository 삭제시 띄워주는 Dialog에도 같은 Dialog를 재활용할 수 있도록 Custom할 예정
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
        setPickerValue()
        setPickerCycle()
        dialogDismiss()
    }

    override fun onResume() {
        getDeviceSize()
        super.onResume()
    }

    //defaultDisplay Deprecated로 인한 Version 처리
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

    private fun setPickerValue() {
        val pickerMap: Map<Int, NumberPicker>
        = mapOf(0 to binding.pickerYear, 1 to binding.pickerMonth, 2 to binding.pickerDay)

        pickerMap.forEach{ pickeritem ->
            val picker = DATE.findType(pickeritem.key)
            pickeritem.value.run {
                maxValue = picker.MAX
                minValue = picker.MIN
                value = picker.INIT
            }
        }
    }

    private fun dialogDismiss() {
        binding.buttonComplete.setOnClickListener {
            val year = binding.pickerYear.value.toString()
            val month = binding.pickerMonth.value.toString()
            val day = binding.pickerDay.value.toString()
            listener.applyDate(year, month, day)
            dismiss()
        }
    }

    enum class DATE(val type: Int?, val MIN: Int, val MAX: Int, val INIT: Int) {
        YEAR(0,1910, 2021,1990),
        MONTH(1,1,12,6),
        DAY(2,1,31,15);

        companion object {
            fun findType(type: Int): DATE {
                return values().find {
                    it.type == type
                } ?: throw IllegalArgumentException("--picker type error--")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}