package com.bangkit.gamastik.data.remote


import com.bangkit.gamastik.data.model.auth.login.LoginRequest
import com.bangkit.gamastik.data.model.auth.login.LoginResponse
import com.bangkit.gamastik.data.model.auth.logout.LogoutResponse
import com.bangkit.gamastik.data.model.auth.register.RegisterRequest
import com.bangkit.gamastik.data.model.auth.register.RegisterResponse
import com.bangkit.gamastik.data.model.batik.detail.BatikDetailResponse
import com.bangkit.gamastik.data.model.batik.discovery.BatikDiscoveryResponseItem
import com.bangkit.gamastik.data.model.batik.search.BatikSearchRequest
import com.bangkit.gamastik.data.model.batik.search.BatikSearchResponseItem
import com.bangkit.gamastik.data.model.quiz.QuizResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface AppService {

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("logout")
    suspend fun logout(): Response<LogoutResponse>

    @GET("batik/discovery")
    suspend fun getBatikDiscovery(): Response<List<BatikDiscoveryResponseItem>>

    @GET("batik/{id}")
    suspend fun getBatikDetail(@Path("id") id: Int): Response<BatikDetailResponse>

    @GET("batik/search")
    suspend fun getBatikSearch(@Body request: BatikSearchRequest): Response<List<BatikSearchResponseItem>>

    @GET("quiz")
    suspend fun getQuizQuestion(): Response<QuizResponse>
}