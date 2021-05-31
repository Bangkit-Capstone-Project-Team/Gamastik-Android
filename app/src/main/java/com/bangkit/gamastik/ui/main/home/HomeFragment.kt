package com.bangkit.gamastik.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.gamastik.data.model.batik.discovery.BatikDiscoveryResponseItem
import com.bangkit.gamastik.databinding.FragmentHomeBinding
import com.bangkit.gamastik.ui.base.BaseFragment
import com.bangkit.gamastik.ui.feature.discovery.batikdetail.BatikDetailActivity
import com.bangkit.gamastik.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(), HomeAdapter.DiscoveryItemListener {

    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        setRecyclerView()
        getDiscoveryBatik()
    }

    private fun setRecyclerView() {
        adapter = HomeAdapter(this)
        binding.rvBatik.layoutManager = GridLayoutManager(context, 2)
        binding.rvBatik.setHasFixedSize(true)
        binding.rvBatik.adapter = adapter
    }


    private fun getDiscoveryBatik() {
        homeViewModel.batikDiscovery.observe(viewLifecycleOwner, {
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
                    Toast.makeText(requireContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClicked(item: BatikDiscoveryResponseItem?) {
        val intent = Intent(requireContext(), BatikDetailActivity::class.java)
        intent.putExtra(BatikDetailActivity.EXTRA_DATA, item?.id)
        startActivity(intent)
    }
}