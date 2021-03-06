package com.bangkit.gamastik.data.repository

import com.bangkit.gamastik.data.model.auth.login.LoginRequest
import com.bangkit.gamastik.data.model.auth.register.RegisterRequest
import com.bangkit.gamastik.data.model.batik.byregion.BatikByRegionRequest
import com.bangkit.gamastik.data.model.batik.search.BatikSearchRequest
import com.bangkit.gamastik.data.remote.RemoteDataSource
import com.bangkit.gamastik.utils.remoteOnlyOperation
import retrofit2.http.Body
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    fun getUserId() = remoteOnlyOperation(
        networkCall = { remoteDataSource.getUserId() }
    )

    fun getProfile(id: Int) = remoteOnlyOperation(
        networkCall = { remoteDataSource.getProfile(id) }
    )

    fun register(@Body request: RegisterRequest) = remoteOnlyOperation(
        networkCall = { remoteDataSource.register(request) }
    )

    fun login(@Body request: LoginRequest) = remoteOnlyOperation(
        networkCall = { remoteDataSource.login(request) }
    )

    fun logout() = remoteOnlyOperation(
        networkCall = { remoteDataSource.logout() }
    )

    fun getBatikDiscovery() = remoteOnlyOperation(
        networkCall = { remoteDataSource.getBatikDiscovery() }
    )

    fun getRRegionList() = remoteOnlyOperation(
        networkCall = { remoteDataSource.getRegionList() }
    )

    fun getBatikDetail(id: Int) = remoteOnlyOperation(
        networkCall = { remoteDataSource.getBatikDetail(id) }
    )

    fun getBatikByRegion(request: BatikByRegionRequest) = remoteOnlyOperation(
        networkCall = { remoteDataSource.getBatikByRegion(request) }
    )

    fun getBatikSearch(request: BatikSearchRequest) = remoteOnlyOperation(
        networkCall = { remoteDataSource.getBatikSearch(request) }
    )

    fun getQuizQuestion() = remoteOnlyOperation(
        networkCall = { remoteDataSource.getQuizQuestion() }
    )
}