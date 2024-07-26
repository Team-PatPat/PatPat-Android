package com.simply407.patpat.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.simply407.patpat.MainActivity
import com.simply407.patpat.R
import com.simply407.patpat.data.model.LoginRequest
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.ActivityLoginBinding
import com.simply407.patpat.ui.join.JoinInfoActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: LoginViewModel

    // 카카오계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            // 카카오계정으로 로그인 실패
            Log.d(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            // 카카오계정으로 로그인 성공
            Log.d(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")

            SharedPreferencesManager.setUserIsLoggedIn(true)

            val loginRequest = LoginRequest("KAKAO", token.accessToken)
            viewModel.postLogin(loginRequest)
        }
    }

    val TAG = "LoginActivity1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        kakaoLogin()
        observeData()
    }

    private fun kakaoLogin() {
        binding.buttonKakaoLogin.setOnClickListener {

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                // 카카오톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        // 카카오톡으로 로그인 실패
                        // Log.d(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        // 카카오톡으로 로그인 성공
                        Log.d(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")

                        SharedPreferencesManager.setUserIsLoggedIn(true)

                        val loginRequest = LoginRequest("KAKAO", token.accessToken)
                        viewModel.postLogin(loginRequest)
                    }
                }
            } else {
                // 카카오계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun observeData() {
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { loginResponse ->
                // 로그인 성공 처리
                Log.d(TAG, "로그인 성공: $loginResponse")
                SharedPreferencesManager.setUserAccessToken(loginResponse.accessToken)
                viewModel.getUserInfo(loginResponse.accessToken)
            }
            result.onFailure { error ->
                // 로그인 실패 처리
                Log.d(TAG, "로그인 실패: $error")
            }
        }

        viewModel.userInfoResult.observe(this) { result ->
            result.onSuccess { userInfoResponse ->
                // 유저 정보 성공 처리
                Log.d(TAG, "유저 정보 성공: $userInfoResponse")

                // 닉네임 설정을 안한 유저
                if (userInfoResponse.name == null) {
                    moveToJoinInfoActivity()
                } else {
                    // 닉네임 설정을 한 유저
                    moveToMain()
                }

            }
            result.onFailure { error ->
                // 로그인 실패 처리
                Log.d(TAG, "유저 정보 실패: $error")
            }
        }

    }

    private fun moveToJoinInfoActivity() {
        val intent = Intent(this, JoinInfoActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}