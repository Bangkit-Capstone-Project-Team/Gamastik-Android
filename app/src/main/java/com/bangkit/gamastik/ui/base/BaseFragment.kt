package com.bangkit.gamastik.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bangkit.gamastik.data.local.PreferencesHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    fun setToken(token: String?){
        preferencesHelper.setUserToken(token)
    }

    fun setUserName(name: String?){
        preferencesHelper.setUserName(name)
    }

    fun getToken() = preferencesHelper.getUserToken()

    fun getUserName() = preferencesHelper.getUserName()

}