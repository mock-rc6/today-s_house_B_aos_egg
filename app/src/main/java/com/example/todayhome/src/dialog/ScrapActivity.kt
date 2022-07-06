package com.example.todayhome.src.dialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.todayhome.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ScrapActivity : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.findViewById<Button>(R.id.button_bottom_sheet)?.setOnClickListener {
            dismiss()
        }
    }
}