package com.simply407.patpat.ui.join

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputEditText
import com.simply407.patpat.MainActivity
import com.simply407.patpat.R

class JoinInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_join_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addFragment(JOIN_NICK_NAME_FRAGMENT, false, null)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        if(currentFocus is TextInputEditText) {
            currentFocus!!.clearFocus()
        }

        return super.dispatchTouchEvent(ev)
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
                JOIN_NICK_NAME_FRAGMENT -> JoinNickNameFragment()
                JOIN_MBTI_FIRST_FRAGMENT -> JoinMbtiFirstFragment()
                JOIN_MBTI_SECOND_FRAGMENT -> JoinMbtiSecondFragment()
                JOIN_MBTI_THIRD_FRAGMENT -> JoinMbtiThirdFragment()
                JOIN_MBTI_FOURTH_FRAGMENT -> JoinMbtiFourthFragment()
                JOIN_MBTI_COMPLETE_FRAGMENT -> JoinMbtiCompleteFragment()
                else -> Fragment()
            }
            // 새 프래그먼트를 추가합니다. 태그를 사용하여 찾을 수 있도록 합니다.
            fragmentTransaction.add(R.id.fragment_container_join_info, newFragment, name)
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

    fun removeFragment(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    fun animationMbti(
        context: Context?,
        textViewFirstMbti: TextView,
        textViewSecondMbti: TextView,
        onAnimationEnd: () -> Unit
    ) {
        val fadeOut = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.mbti_fade_out)
        fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {
                // 애니메이션 시작 시 처리할 내용
            }

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                onAnimationEnd()
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {
                // 애니메이션 반복 시 처리할 내용
            }
        })

        // 텍스트 뷰에 애니메이션 시작
        textViewFirstMbti.startAnimation(fadeOut)
        textViewSecondMbti.startAnimation(fadeOut)
    }

    companion object {
        const val JOIN_NICK_NAME_FRAGMENT = "JoinNickNameFragment"
        const val JOIN_MBTI_FIRST_FRAGMENT = "JoinMbtiFirstFragment"
        const val JOIN_MBTI_SECOND_FRAGMENT = "JoinMbtiSecondFragment"
        const val JOIN_MBTI_THIRD_FRAGMENT = "JoinMbtiThirdFragment"
        const val JOIN_MBTI_FOURTH_FRAGMENT = "JoinMbtiFourthFragment"
        const val JOIN_MBTI_COMPLETE_FRAGMENT = "JoinMbtiCompleteFragment"
    }
}