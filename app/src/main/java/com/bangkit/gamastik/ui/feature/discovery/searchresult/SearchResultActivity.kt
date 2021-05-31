package com.bangkit.gamastik.ui.feature.discovery.searchresult

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.gamastik.data.model.batik.search.BatikSearchRequest
import com.bangkit.gamastik.data.model.batik.search.BatikSearchResponseItem
import com.bangkit.gamastik.databinding.ActivitySearchResultBinding
import com.bangkit.gamastik.ui.base.BaseActivity
import com.bangkit.gamastik.ui.feature.discovery.batikdetail.BatikDetailActivity
import com.bangkit.gamastik.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultActivity : BaseActivity(), SearchResultAdapter.ResultItemListener {
    private val viewModel: SearchResultViewModel by viewModels()
    lateinit var binding: ActivitySearchResultBinding
    private lateinit var adapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val keyword = intent.getStringExtra(EXTRA_DATA)
        setRecyclerView()
        getSearchResult(BatikSearchRequest(keyword))

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSearchResult(BatikSearchRequest(keyword))

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setRecyclerView() {
        adapter = SearchResultAdapter(this)
        binding.rvBatik.layoutManager = GridLayoutManager(this, 2)
        binding.rvBatik.setHasFixedSize(true)
        binding.rvBatik.adapter = adapter
    }


    private fun getSearchResult(request: BatikSearchRequest) {
        binding.etSearch.setQuery(request.search, false)
        viewModel.batikSearch(request)?.observe(this, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val data = it.data
                    if (data != null) {
                        it.data.let { it -> adapter.setItems(ArrayList(it)) }
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

    override fun onClicked(item: BatikSearchResponseItem?) {
        val intent = Intent(this, BatikDetailActivity::class.java)
        intent.putExtra(BatikDetailActivity.EXTRA_DATA, item?.id)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}