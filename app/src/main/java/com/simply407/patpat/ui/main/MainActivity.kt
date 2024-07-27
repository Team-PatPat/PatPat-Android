package com.simply407.patpat.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.simply407.patpat.R
import com.simply407.patpat.databinding.ActivityMainBinding
import com.simply407.patpat.ui.home.HomeDetailFragment
import com.simply407.patpat.ui.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val manager =supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
       }

        addFragment(HOME_FRAGMENT, false, null)
        bottomNavigation()
    }

    fun bottomNavigation() {
        binding.bottomNavigationViewMain.run {

            itemIconTintList = null

            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home_menu_item -> {
                        addFragment(HOME_FRAGMENT, false, null)
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

    fun addFragment(name: String, addToBackStack: Boolean, bundle: Bundle?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // 현재 추가된 모든 프래그먼트를 숨깁니다.
        val currentFragments = supportFragmentManager.fragments
        for (fragment in currentFragments) {
            fragmentTransaction.hide(fragment)
        }

        // 이름에 따라 새 프래그먼트를 찾거나 생성합니다.
        var newFragment = supportFragmentManager.findFragmentByTag(name)

        if (newFragment == null) {
            newFragment = when(name) {
                HOME_FRAGMENT -> HomeFragment()
                HOME_DETAIL_FRAGMENT -> HomeDetailFragment()
                else -> Fragment()
            }
            // 새 프래그먼트를 추가합니다. 태그를 사용하여 찾을 수 있도록 합니다.
            fragmentTransaction.add(R.id.fragment_container_main, newFragment, name)
        } else {
            // 프래그먼트가 이미 존재한다면 보여줍니다.
            fragmentTransaction.show(newFragment)
        }

        newFragment.arguments = bundle

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(name)
        }

        fragmentTransaction.commit()
    }

    companion object {
        const val HOME_FRAGMENT = "HomeFragment"
        const val HOME_DETAIL_FRAGMENT = "HomeDetailFragment"
    }
}