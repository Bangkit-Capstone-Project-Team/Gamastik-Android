package com.bangkit.gamastik.ui.feature.discovery.batikdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.gamastik.data.model.batik.detail.BatikDetailResponse
import com.bangkit.gamastik.data.repository.AppRepository
import com.bangkit.gamastik.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BatikDetailViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val batikDetail: (Int) -> LiveData<Resource<BatikDetailResponse>>? =
        { value -> value.let { repository.getBatikDetail(it) } }

}
