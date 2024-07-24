package com.simply407.patpat.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.simply407.patpat.R
import com.simply407.patpat.databinding.ActivityOnboardingBinding
import com.simply407.patpat.ui.login.LoginActivity

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
                    override fun onOnboardingNextButtonClick() {
                        viewPagerOnboarding.currentItem = 1
                    }

                    override fun onOnboardingStartButtonClick() {
                        val intent = Intent(this@OnboardingActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                })

            }
        }
    }
}