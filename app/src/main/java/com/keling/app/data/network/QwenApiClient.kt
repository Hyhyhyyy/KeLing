package com.keling.app.data.network

import com.keling.app.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QwenApiClient @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.QWEN_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()

    val api: QwenApi = retrofit.create(QwenApi::class.java)
    val openAiApi: OpenAiApi = retrofit.create(OpenAiApi::class.java)
}
