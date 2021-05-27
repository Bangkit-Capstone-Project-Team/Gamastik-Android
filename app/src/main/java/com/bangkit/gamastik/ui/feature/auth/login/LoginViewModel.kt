package com.bangkit.gamastik.ui.feature.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.gamastik.data.model.auth.login.LoginRequest
import com.bangkit.gamastik.data.model.auth.login.LoginResponse
import com.bangkit.gamastik.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import gov.ulama.uttp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val login: (LoginRequest?) -> LiveData<Resource<LoginResponse>>? =
        { value -> value?.let { repository.login(it) } }

}
