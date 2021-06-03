package com.bangkit.gamastik.ui.feature.discovery.searchresult

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.gamastik.data.model.batik.search.BatikSearchRequest
import com.bangkit.gamastik.data.model.batik.search.BatikSearchResponseItem
import com.bangkit.gamastik.data.repository.AppRepository
import com.bangkit.gamastik.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val batikSearch: (BatikSearchRequest) -> LiveData<Resource<List<BatikSearchResponseItem>>>? =
        { value -> value.let { repository.getBatikSearch(it) } }

}
