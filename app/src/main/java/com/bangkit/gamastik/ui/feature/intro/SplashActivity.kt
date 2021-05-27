package com.bangkit.gamastik.ui.feature.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bangkit.gamastik.R
import com.bangkit.gamastik.ui.base.BaseActivity
import com.bangkit.gamastik.ui.feature.auth.login.LoginActivity
import com.bangkit.gamastik.ui.main.MainActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
            checklogin()
        }, 3000)

    }

    private fun checklogin() {
        val intent: Intent = if (getToken() != null) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

}