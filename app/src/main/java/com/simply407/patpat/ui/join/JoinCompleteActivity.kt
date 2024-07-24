package com.simply407.patpat.ui.join

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.simply407.patpat.R
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.ActivityJoinCompleteBinding

class JoinCompleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityJoinCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        onClickButton()
    }

    private fun onClickButton() {
        binding.buttonCheckJoinComplete.setOnClickListener {
            SharedPreferencesManager.setFirstJoinComplete(true)
        }
    }
}