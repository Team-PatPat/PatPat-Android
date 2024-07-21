package com.simply407.patpat.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.simply407.patpat.MainActivity
import com.simply407.patpat.R
import com.simply407.patpat.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initUI()
    }

    private fun initUI() {
        binding.run {
            viewPagerOnboarding.run {
                viewPagerOnboarding.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = OnboardingAdapter(object : OnboardingAdapter.OnboardingClickListener {
                    override fun onOnboardingButtonClick() {
                        val intent = Intent(this@OnboardingActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                })

            }
        }
    }
}