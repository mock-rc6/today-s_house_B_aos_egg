package com.example.todayhome.src.dialog

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.todayhome.R
import com.example.todayhome.config.store.itemCart.CartBody
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.w3c.dom.Text


class BuyActivity(context: Context) : BottomSheetDialogFragment() {

    private val mContext: Context = context
    private var number: String = "0"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_buy, container, false)
        val plus: ImageButton = view.findViewById(R.id.plus)
        val minus: ImageButton = view.findViewById(R.id.minus)
        var itemCount: TextView = view.findViewById(R.id.itemCount)
        var cartButton: ImageButton = view.findViewById(R.id.cartButton)
        val buyButton: ImageButton = view.findViewById(R.id.buyButton)
        var itemMoney: TextView = view.findViewById(R.id.itemMoney)
        var itemprice: TextView = view.findViewById(R.id.itemprice)

        plus.setOnClickListener {
            Toast.makeText(mContext, "확인", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        minus.setOnClickListener {
            Toast.makeText(mContext, "닫기", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return view
    }


}

