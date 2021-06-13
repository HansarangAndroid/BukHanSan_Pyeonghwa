package com.example.lecturesopt28th.githubrepo.view

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.toSpannable
import androidx.fragment.app.DialogFragment
import com.example.lecturesopt28th.databinding.FragmentDeleteDialogBinding

@Deprecated("Use feature/login Module")
class DeleteDialogFragment(private val callback: DeleteCallback): DialogFragment() {
    private var _binding: FragmentDeleteDialogBinding? = null
    private val binding get() = _binding ?: error("view binding error")

    interface DeleteCallback{
        fun delete()
        fun cancel()
        fun exit()
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.30).toInt()
        dialog!!.window?.setLayout(width, height)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickButtons()
        val span = binding.textviewDeleteRepo.text
        span.toSpannable().setSpan(ForegroundColorSpan(Color.RED), 13, 19, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
    }

    private fun clickButtons() {
        binding.buttonDelete.setOnClickListener {
            callback.delete()
            dismiss()
        }
        binding.buttonCancel.setOnClickListener {
            callback.cancel()
            dismiss()
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        callback.exit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}