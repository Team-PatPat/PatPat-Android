package com.simply407.patpat.ui.letter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.simply407.patpat.R
import com.simply407.patpat.databinding.ActivityChattingBinding
import com.simply407.patpat.databinding.ActivityLetterBinding
import com.simply407.patpat.ui.chat.ChattingFragment

class LetterActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLetterBinding
    private val manager =supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityLetterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }

        val transaction=manager.beginTransaction()
        val fragmentLetter = LetterFragment()
    }

}