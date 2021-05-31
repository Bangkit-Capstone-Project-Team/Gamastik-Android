package com.bangkit.gamastik.ui.main.home

import androidx.lifecycle.ViewModel
import com.bangkit.gamastik.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val batikDiscovery =  repository.getBatikDiscovery()

}