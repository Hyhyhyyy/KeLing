package com.keling.app.data.repository

import com.keling.app.BuildConfig
import com.keling.app.data.model.AiProvider
import com.keling.app.data.network.OpenAiRequest
import com.keling.app.data.network.QwenApiClient
import com.keling.app.data.network.QwenRequest
import com.keling.app.data.preferences.AiPreferencesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QwenRepository @Inject constructor(
    private val qwenApiClient: QwenApiClient,
    private val aiPreferencesRepository: AiPreferencesRepository
) {
    suspend fun chat(messages: List<QwenRequest.Message>): String {
        // 1. 获取当前配置
        val (provider, modelId) = aiPreferencesRepository.currentAiConfig.first()

        // 2. 准备参数
        // 优先使用 local.properties 注入的 Key，如果没有，降级到默认 QWEN_API_KEY (为了兼容旧逻辑)
        // 实际上 AiConfig Enum 里已经映射了 BuildConfig 的 Key
        var apiKey = provider.apiKey
        if (apiKey.isBlank() && provider == AiProvider.QWEN) {
             // Fallback for Qwen legacy key
             apiKey = aiPreferencesRepository.apiKey.first()
             if (apiKey.isBlank()) apiKey = BuildConfig.QWEN_API_KEY
        }

        if (apiKey.isBlank()) {
            return "Error: API Key not found for ${provider.displayName}"
        }

        val authHeader = "Bearer $apiKey"
        // 拼接完整的 Endpoint URL (Retrofit @Url 需要)
        // 注意：各厂商 BaseUrl 通常以 / 结尾，加上 chat/completions
        // 如果 BaseUrl 包含 /v1/ 之类，需确保拼接正确
        val fullUrl = "${provider.baseUrl.trimEnd('/')}/chat/completions"

        return try {
            // 3. 构造请求 (转换 Message 格式)
            val openAiMessages = messages.map {
                OpenAiRequest.Message(it.role, it.content)
            }

            val request = OpenAiRequest(
                model = modelId,
                messages = openAiMessages
            )

            // 4. 发起请求
            val response = qwenApiClient.openAiApi.chatCompletions(fullUrl, authHeader, request)

            if (response.isSuccessful) {
                val content = response.body()?.choices?.firstOrNull()?.message?.content ?: ""
                if (content.isBlank()) "Error: Empty response content" else content
            } else {
                "Error: ${response.code()} ${response.message()} - ${response.errorBody()?.string()}"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Exception: ${e.message}"
        }
    }
}
