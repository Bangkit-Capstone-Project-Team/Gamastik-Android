package com.bangkit.gamastik.data.remote

import com.bangkit.gamastik.data.model.auth.login.LoginRequest
import com.bangkit.gamastik.data.model.auth.register.RegisterRequest
import com.bangkit.gamastik.data.model.batik.byregion.BatikByRegionRequest
import com.bangkit.gamastik.data.model.batik.search.BatikSearchRequest
import com.bangkit.gamastik.utils.BaseDataSource
import retrofit2.http.Body
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val appService: AppService,
) : BaseDataSource() {

    suspend fun getUserId() = getResult { appService.getUserId() }

    suspend fun getProfile(id: Int) = getResult { appService.getProfile(id) }

    suspend fun register(@Body request: RegisterRequest) =
        getResult { appService.register(request) }

    suspend fun login(@Body request: LoginRequest) = getResult { appService.login(request) }

    suspend fun logout() = getResult { appService.logout() }

    suspend fun getBatikDiscovery() = getResult { appService.getBatikDiscovery() }

    suspend fun getRegionList() = getResult { appService.getRegionList() }

    suspend fun getBatikDetail(id: Int) = getResult { appService.getBatikDetail(id) }

    suspend fun getBatikByRegion(request: BatikByRegionRequest) =
        getResult { appService.getBatikByRegion(request) }

    suspend fun getBatikSearch(request: BatikSearchRequest) =
        getResult { appService.getBatikSearch(request) }

    suspend fun getQuizQuestion() = getResult { appService.getQuizQuestion() }

}