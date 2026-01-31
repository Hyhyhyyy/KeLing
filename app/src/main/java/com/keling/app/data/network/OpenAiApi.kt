package com.keling.app.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Url

interface OpenAiApi {
    // 使用 @Url 动态传入完整的 endpoint url (provider.baseUrl + "chat/completions")
    @POST
    suspend fun chatCompletions(
        @Url url: String,
        @Header("Authorization") apiKey: String,
        @Body request: OpenAiRequest
    ): Response<OpenAiResponse>
}
