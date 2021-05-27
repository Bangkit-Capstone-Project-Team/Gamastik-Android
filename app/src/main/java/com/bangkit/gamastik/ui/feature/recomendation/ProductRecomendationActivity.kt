package com.bangkit.gamastik.ui.feature.recomendation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.gamastik.R
import com.bangkit.gamastik.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductRecomendationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_recomendation)
    }
}