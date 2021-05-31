package com.bangkit.gamastik.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.gamastik.data.model.batik.discovery.BatikDiscoveryResponseItem
import com.bangkit.gamastik.databinding.FragmentHomeBinding
import com.bangkit.gamastik.ui.base.BaseFragment
import com.bangkit.gamastik.ui.feature.auth.login.LoginActivity
import com.bangkit.gamastik.ui.feature.discovery.batikdetail.BatikDetailActivity
import com.bangkit.gamastik.ui.feature.discovery.searchresult.SearchResultActivity
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
        getUserId()
        setRecyclerView()
        getDiscoveryBatik()

        binding.ivLogout.setOnClickListener {
            logout()
        }

        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(requireContext(), SearchResultActivity::class.java)
                intent.putExtra(SearchResultActivity.EXTRA_DATA, query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setRecyclerView() {
        adapter = HomeAdapter(this)
        binding.rvBatik.layoutManager = GridLayoutManager(context, 2)
        binding.rvBatik.setHasFixedSize(true)
        binding.rvBatik.adapter = adapter
    }

    private fun getUserId() {
        homeViewModel.userId.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val data = it.data
                    if (data != null) {
                        data.id?.let { it1 -> getProfile(it1) }
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

    private fun getProfile(id: Int) {
        homeViewModel.profile(id).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    val data = it.data
                    if (data != null) {
                        binding.tvUserName.text = StringBuilder("Hello, ${data.data?.name}")
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


    private fun logout() {
        homeViewModel.logout.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    setToken(null)
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
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