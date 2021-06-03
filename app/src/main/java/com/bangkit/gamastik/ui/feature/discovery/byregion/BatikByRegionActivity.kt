package com.bangkit.gamastik.ui.feature.discovery.byregion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.gamastik.data.model.batik.BatikByRegion
import com.bangkit.gamastik.data.model.batik.byregion.BatikByRegionRequest
import com.bangkit.gamastik.databinding.ActivityBatikByRegionBinding
import com.bangkit.gamastik.ui.feature.discovery.batikdetail.BatikDetailActivity
import com.bangkit.gamastik.ui.feature.discovery.searchresult.SearchResultActivity
import com.bangkit.gamastik.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BatikByRegionActivity : AppCompatActivity(), BatikByRegionAdapter.ResultItemListener {
    lateinit var binding: ActivityBatikByRegionBinding
    private val viewModel: BatikByRegionViewModel by viewModels()
    private lateinit var adapter: BatikByRegionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatikByRegionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val keyword = intent.getStringExtra(SearchResultActivity.EXTRA_DATA)
        setRecyclerView()
        getBatikByRegion(BatikByRegionRequest(keyword))

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setRecyclerView() {
        adapter = BatikByRegionAdapter(this)
        binding.rvBatik.layoutManager = GridLayoutManager(this, 2)
        binding.rvBatik.setHasFixedSize(true)
        binding.rvBatik.adapter = adapter
    }


    private fun getBatikByRegion(request: BatikByRegionRequest) {
        binding.tvRegion.text = request.daerah
        viewModel.batikByRegion(request)?.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val data = it.data?.data
                    if (data != null) {
                        adapter.setItems(ArrayList(data))
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

    override fun onClicked(item: BatikByRegion?) {
        val intent = Intent(this, BatikDetailActivity::class.java)
        intent.putExtra(BatikDetailActivity.EXTRA_DATA, item?.id)
        startActivity(intent)
    }
}