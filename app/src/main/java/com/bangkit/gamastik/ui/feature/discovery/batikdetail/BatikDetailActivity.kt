package com.bangkit.gamastik.ui.feature.discovery.batikdetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bangkit.gamastik.R
import com.bangkit.gamastik.databinding.ActivityBatikDetailBinding
import com.bangkit.gamastik.ui.base.BaseActivity
import com.bangkit.gamastik.utils.Resource
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BatikDetailActivity : BaseActivity() {
    lateinit var binding: ActivityBatikDetailBinding
    private val viewModel: BatikDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatikDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val id = intent.getIntExtra(EXTRA_DATA, 0)
        getBatikDetail(id)

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getBatikDetail(id: Int) {
        viewModel.batikDetail(id)?.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val data = it.data?.data
                    if (data != null) {
                        binding.apply {
                            tvBatikName.text = data.namaBatik
                            tvBatikRegion.text = data.daerahBatik
                            tvBatikDesc.text = data.maknaBatik
                            Glide.with(binding.root).load(data.linkBatik)
                                .placeholder(R.drawable.img_placeholder).into(ivBatikResult)
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}