package com.simply407.patpat.ui.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.AppBarLayout
import com.kakao.sdk.common.util.Utility
import com.simply407.patpat.R
import com.simply407.patpat.databinding.ActivityChattingBinding

class ChattingActivity :AppCompatActivity() {

    private lateinit var binding: ActivityChattingBinding
    private val manager =supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }
        val roomName =intent.getStringExtra("name")
        val transaction=manager.beginTransaction()
        val fragmentChat = ChattingFragment()

        transaction.replace(R.id.chattingfragment,fragmentChat)
        transaction.addToBackStack(null)
        transaction.commit()


    }
}