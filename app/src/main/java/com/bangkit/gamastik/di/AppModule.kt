package com.bangkit.gamastik.di

import android.content.Context
import com.bangkit.gamastik.BuildConfig.BASE_URL
import com.bangkit.gamastik.BuildConfig.DEBUG_URL
import com.bangkit.gamastik.data.local.PreferencesHelper
import com.bangkit.gamastik.data.remote.AppService
import com.bangkit.gamastik.data.remote.RemoteDataSource
import com.bangkit.gamastik.data.repository.AppRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext appContext: Context) = PreferencesHelper(appContext)

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(DEBUG_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    internal fun provideOkHttpClient(preferencesHelper: PreferencesHelper): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .retryOnConnectionFailure(false)

        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Bearer " + preferencesHelper.getUserToken())
                .header("Accept", "application/json")
                .header("Accept-Encoding", "identity")
                .addHeader("Connection", "close")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return httpClientBuilder.build()

    }

    @Provides
    fun provideAppService(retrofit: Retrofit): AppService =
        retrofit.create(AppService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        appService: AppService,
    ) = RemoteDataSource(appService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource) = AppRepository(remoteDataSource)

}