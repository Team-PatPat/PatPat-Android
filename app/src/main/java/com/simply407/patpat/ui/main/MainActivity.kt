package com.simply407.patpat.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.simply407.patpat.R
import com.simply407.patpat.databinding.ActivityMainBinding
import com.simply407.patpat.ui.chatting.ChattingFragment
import com.simply407.patpat.ui.home.HomeDetailFragment
import com.simply407.patpat.ui.home.HomeFragment
import com.simply407.patpat.ui.storagebox.StorageBoxFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val manager =supportFragmentManager

    private var backPressedOnce = false
    private val handler = Handler(Looper.getMainLooper())
    private val backPressRunnable = Runnable {
        backPressedOnce = false
    }

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

        // 뒤로가기 버튼 처리
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                    backPressedOnce = false
                } else {
                    if (backPressedOnce) {
                        handler.removeCallbacks(backPressRunnable)
                        finish()
                    } else {
                        backPressedOnce = true
                        Toast.makeText(this@MainActivity, "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
                        handler.postDelayed(backPressRunnable, 2000)
                    }
                }
            }
        })
    }

    fun bottomNavigation() {
        binding.bottomNavigationViewMain.run {

            itemIconTintList = null

            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home_menu_item -> {
                        removeAllBackStack()
                        addFragment(HOME_FRAGMENT, false, null)
                        return@setOnItemSelectedListener true
                    }

                    R.id.inbox_menu_item -> {
                        removeAllBackStack()
                        addFragment(STORAGE_BOX_FRAGMENT, false, null)
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
                STORAGE_BOX_FRAGMENT -> StorageBoxFragment()
                CHATTING_FRAGMENT -> ChattingFragment()
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

    fun removeFragment(name: String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun removeAllBackStack() {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun showBottomNavigation() {
        binding.bottomNavigationViewMain.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomNavigationViewMain.visibility = View.GONE
    }

    fun logLongMessage(tag: String, message: String) {
        val maxLogSize = 1000
        for (i in 0..message.length / maxLogSize) {
            val start = i * maxLogSize
            val end = Math.min((i + 1) * maxLogSize, message.length)
            Log.d(tag, message.substring(start, end))
        }
    }

    companion object {
        const val HOME_FRAGMENT = "HomeFragment"
        const val HOME_DETAIL_FRAGMENT = "HomeDetailFragment"
        const val STORAGE_BOX_FRAGMENT = "StorageBoxFragment"
        const val CHATTING_FRAGMENT = "ChattingFragment"
    }
}