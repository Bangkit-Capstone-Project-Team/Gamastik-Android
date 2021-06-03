package com.bangkit.gamastik.ui.feature.intro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bangkit.gamastik.R
import com.bangkit.gamastik.databinding.ActivitySplashBinding
import com.bangkit.gamastik.ui.base.BaseActivity
import com.bangkit.gamastik.ui.feature.auth.login.LoginActivity
import com.bangkit.gamastik.ui.main.MainActivity
import com.bumptech.glide.Glide

class SplashActivity : BaseActivity() {
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.drawable.img_splash).into(binding.imgSplash)
        Handler(mainLooper).postDelayed({
            checklogin()
        }, 3000)

    }

    private fun checklogin() {
        val intent: Intent = if (getToken() != null && getToken()?.isNotEmpty() == true) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

}