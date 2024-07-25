package com.simply407.patpat

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import com.simply407.patpat.databinding.ActivityMainBinding
import com.simply407.patpat.ui.chat.ChattingFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val manager =supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
       }

        bottomNavigation()
    }

    fun bottomNavigation() {
        binding.bottomNavigationViewMain.run {

            itemIconTintList = null

            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home_menu_item -> {
                        return@setOnItemSelectedListener true
                    }

                    R.id.inbox_menu_item -> {
                        return@setOnItemSelectedListener true
                    }

                    R.id.user_menu_item -> {
                        return@setOnItemSelectedListener true
                    }

                    else -> return@setOnItemSelectedListener false
                }
            }
        }
    }
}