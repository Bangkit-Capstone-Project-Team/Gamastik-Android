package com.bangkit.gamastik.ui.feature.discovery.byregion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.gamastik.data.model.batik.byregion.BatikByRegionResponse
import com.bangkit.gamastik.data.model.batik.byregion.BatikByRegionRequest
import com.bangkit.gamastik.data.repository.AppRepository
import com.bangkit.gamastik.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BatikByRegionViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val batikByRegion: (BatikByRegionRequest) -> LiveData<Resource<BatikByRegionResponse>>? =
        { value -> value.let { repository.getBatikByRegion(it) } }

}
