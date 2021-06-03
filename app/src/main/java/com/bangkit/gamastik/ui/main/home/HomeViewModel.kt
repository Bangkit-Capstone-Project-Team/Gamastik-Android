package com.bangkit.gamastik.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.gamastik.data.model.auth.profile.ProfileResponse
import com.bangkit.gamastik.data.model.batik.search.BatikSearchRequest
import com.bangkit.gamastik.data.model.batik.search.BatikSearchResponseItem
import com.bangkit.gamastik.data.repository.AppRepository
import com.bangkit.gamastik.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val batikDiscovery =  repository.getBatikDiscovery()

    val logout =  repository.logout()

    val userId =  repository.getUserId()

    val regionList =  repository.getRRegionList()

    val profile: (Int) -> LiveData<Resource<ProfileResponse>> =
        { value -> value.let { repository.getProfile(it) } }

}