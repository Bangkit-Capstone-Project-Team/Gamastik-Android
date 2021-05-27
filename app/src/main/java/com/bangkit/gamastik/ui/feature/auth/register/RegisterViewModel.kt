package com.bangkit.gamastik.ui.feature.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.gamastik.data.model.auth.login.LoginRequest
import com.bangkit.gamastik.data.model.auth.login.LoginResponse
import com.bangkit.gamastik.data.model.auth.register.RegisterRequest
import com.bangkit.gamastik.data.model.auth.register.RegisterResponse
import com.bangkit.gamastik.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import gov.ulama.uttp.utils.Resource
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    val register: (RegisterRequest?) -> LiveData<Resource<RegisterResponse>>? =
        { value -> value?.let { repository.register(it) } }

}
