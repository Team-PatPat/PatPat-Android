package com.simply407.patpat.ui.chat

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.simply407.patpat.R
import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.api.patApi

class ChattingPopup (private val activity: ChattingActivity,patApi: patApi) {
    private val dialog: Dialog = Dialog(activity)
    init {

        val inflater = LayoutInflater.from(activity)
        val view: View = inflater.inflate(R.layout.custom_popup, null)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(view)

        val textView: TextView = view.findViewById(R.id.popup_text)
        val continueButton: ImageButton = view.findViewById(R.id.popup_contibtn)
        val finishButton: ImageButton = view.findViewById(R.id.popup_finishbtn)

        // 버튼 클릭 리스너 설정
        continueButton.setOnClickListener {
            dialog.dismiss()
        }

        finishButton.setOnClickListener {
            // 취소 버튼 클릭 시 동작
            activity.postLetter(patApi)
            dialog.dismiss()
        }
    }



    fun show(message: String) {
        dialog.findViewById<TextView>(R.id.popup_text).text = message
        dialog.show()
    }
}