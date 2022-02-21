package com.bhongj.rc_week3

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.bhongj.rc_week3.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    var isAutoLogin = false
    var loginFailCnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("이메일 주소가 맞지 않습니다.")
        builder.setMessage("죄송합니다. 이 이메일 주소를 사용하는\n계정을 찾을 수 없습니다. 다시\n입력하시거나 새로운 계정을 등록하세요.")
        builder.setPositiveButton("실패 횟수 초기화") { dialogInterface: DialogInterface, i: Int ->
            loginFailCnt = 0
            binding.loginTxtFailCnt.text = loginFailCnt.toString()
        }
        builder.setNegativeButton("다시 시도하기") { dialogInterface: DialogInterface, i: Int ->
            loginFailCnt++
            binding.loginTxtFailCnt.text = loginFailCnt.toString()
        }

        binding.loginBtnLogin.setOnClickListener {
            run {
                UserInfoList.forEach {
                    if (binding.loginEdtUserId.text.toString() == it.userID
                        && binding.loginEdtUserPw.text.toString() == it.userPW
                    ) {
                        Toast.makeText(this, it.userName + " : Login Success", Toast.LENGTH_SHORT)
                            .show()
                        isAutoLogin = true
                        loginFailCnt = 0
                        val intent = Intent(this, TalkMainActivity::class.java)
                        intent.putExtra("userName", it.userName)
                        startActivity(intent)
                        finish()
                        return@run
                    }
                }
                builder.show()
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

        var loginIdEnabled = false
        var loginPwEnabled = false
        binding.loginEdtUserId.addTextChangedListener {
            loginIdEnabled = binding.loginEdtUserId.text.toString().length >= 5
            binding.loginBtnLogin.isEnabled = loginPwEnabled && loginIdEnabled
        }
        binding.loginEdtUserPw.addTextChangedListener {
            loginPwEnabled = binding.loginEdtUserPw.text.toString().length >= 4
            binding.loginBtnLogin.isEnabled = loginPwEnabled && loginIdEnabled
        }
    }

    override fun onStart() {
        super.onStart()
//        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show()

        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)
        loginFailCnt = sharedPreferences.getInt("loginFailCnt", 0)
        if (sharedPreferences.getBoolean("isAutoLogin", true)) {
            val intent = Intent(this, TalkMainActivity::class.java)
            intent.putExtra("userId", sharedPreferences.getString("userId", "defaultId"))
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
//        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()

        binding.loginTxtFailCnt.text = loginFailCnt.toString()
    }

    override fun onPause() {
        super.onPause()
//        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show()

        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isAutoLogin", isAutoLogin)
        editor.putInt("loginFailCnt", loginFailCnt)
        if (isAutoLogin) {
            editor.putString("userId", binding.loginEdtUserId.text.toString())
            editor.putString("userPw", binding.loginEdtUserPw.text.toString())
        } else {
            editor.putString("userId", "defaultId")
            editor.putString("userPw", "defaultPw")
        }
        editor.apply()
    }

    override fun onStop() {
        super.onStop()
//        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show()

        binding.loginEdtUserId.setText("")
        binding.loginEdtUserPw.setText("")
    }
}