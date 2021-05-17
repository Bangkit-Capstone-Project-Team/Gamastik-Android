package com.bangkit.gamastik.ui.feature.batik.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.gamastik.R

class ScannerResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner_result)
        supportActionBar?.hide()
    }
}