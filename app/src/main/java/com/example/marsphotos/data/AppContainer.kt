package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.network.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface AppContainer {
    val MarsPhotosRepository: MarsPhotoRepository
}

class DefaultAppContainer: AppContainer{
    private val baseURL = "https://android-kotlin-fun-mars-server.appspot.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseURL)
        .build()

    private val retrofitService: MarsApiService by lazy{
        retrofit.create(MarsApiService::class.java)
    }
    override val MarsPhotosRepository: MarsPhotoRepository by lazy{
        NetworkMarsPhotoRepository(retrofitService)
    }
    }