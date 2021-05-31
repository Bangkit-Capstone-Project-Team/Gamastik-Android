package com.bangkit.gamastik.ui.feature.recomendation

import android.os.Bundle
import com.bangkit.gamastik.databinding.ActivityProductRecomendationBinding
import com.bangkit.gamastik.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductRecomendationActivity : BaseActivity() {

    lateinit var binding: ActivityProductRecomendationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductRecomendationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val keyword = intent.getStringExtra(EXTRA_DATA)
        val url = "https://www.google.co.id/search?safe=strict&output=search&tbm=shop&psb=1&q=$keyword"
        binding.webViewSuite.startLoading(url)
    }

    override fun onBackPressed() {
        if (!binding.webViewSuite.goBackIfPossible()) super.onBackPressed()
    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}