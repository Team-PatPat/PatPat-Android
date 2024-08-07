package com.simply407.patpat.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.simply407.patpat.ui.main.MainActivity
import com.simply407.patpat.R
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.ActivitySplashBinding
import com.simply407.patpat.ui.login.LoginActivity
import com.simply407.patpat.ui.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    val TAG = "SplashActivity1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 뒤로 가기 버튼을 막기 위해 OnBackPressedCallback 추가
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로 가기 버튼을 눌렀을 때 아무 동작도 하지 않음
            }
        })

        checkOnboardingShown()
    }

    private fun moveToOnboarding() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)

            finish()
        }, 2000)
    }

    private fun moveToMain() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }, 2000)
    }

    private fun moveToLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            finish()
        }, 2000)
    }

    private fun checkOnboardingShown() {
        if (SharedPreferencesManager.isOnboardingShown()) {
            kakaoTokenValidation()
        } else {
            moveToOnboarding()
        }
    }

    private fun kakaoTokenValidation() {
        val token = SharedPreferencesManager.getKakaoAccessToken()

        if (!token.isNullOrEmpty()) {
            // 토큰이 존재하는 경우
            if (AuthApiClient.instance.hasToken()) {
                UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                    if (error != null) {
                        if (error is KakaoSdkError && error.isInvalidTokenError()) {
                            // 로그인 필요 - 토큰이 유효하지 않음
                            Log.d(TAG, "토큰 유효성 체크: 토큰이 유효하지 않음.")
                            moveToLogin()
                        } else {
                            // 기타 에러 처리
                            Log.d(TAG, "토큰 유효성 체크 실패", error)
                        }
                    } else {
                        // 토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                        Log.d(TAG, "토큰 유효성 체크 성공: 토큰이 유효함.")

                        AuthApiClient.instance.refreshToken { newToken, error ->
                            if (error != null) {
                                Log.d(TAG, "토큰 갱신 실패", error)
                                moveToLogin()
                            } else {
                                Log.d(TAG, "토큰 갱신 성공")
                                if (newToken != null) {
                                    SharedPreferencesManager.setKakaoAccessToken(newToken.accessToken)
                                }
                            }
                        }

                        moveToMain()
                    }
                }
            } else {
                // 로그인 필요 - 토큰이 유효하지 않음
                Log.d(TAG, "토큰 유효성 체크: 토큰이 유효하지 않음.")
                moveToLogin()
            }
        } else {
            // 토큰이 없으므로 로그인 필요
            Log.d(TAG, "토큰 유효성 체크: 저장된 토큰이 없음.")
            moveToLogin()
        }
    }

}