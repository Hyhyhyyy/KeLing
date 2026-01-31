package com.keling.app.data.model

import com.keling.app.BuildConfig

enum class AiProvider(val id: String, val displayName: String, val baseUrl: String, val apiKey: String) {
    QWEN("qwen", "通义千问 (Qwen)", "https://dashscope.aliyuncs.com/compatible-mode/v1/", BuildConfig.KEY_QWEN),
    DOUBAO("doubao", "豆包 (Doubao)", "https://ark.cn-beijing.volces.com/api/v3/", BuildConfig.KEY_DOUBAO),
    MOONSHOT("moonshot", "月之暗面 (Moonshot)", "https://api.moonshot.cn/v1/", BuildConfig.KEY_MOONSHOT),
    GEMINI("gemini", "Google Gemini", "https://generativelanguage.googleapis.com/v1beta/openai/", BuildConfig.KEY_GEMINI),
    OPENAI("openai", "OpenAI ChatGPT", "https://api.openai.com/v1/", BuildConfig.KEY_OPENAI),
    DEEPSEEK("deepseek", "DeepSeek", "https://api.deepseek.com/", BuildConfig.KEY_DEEPSEEK);

    companion object {
        fun fromId(id: String): AiProvider? = entries.find { it.id == id }
    }
}

data class AiModel(
    val id: String,
    val displayName: String,
    val provider: AiProvider,
    val description: String = ""
)

object AiConfig {
    val providers = AiProvider.entries

    fun getModelsForProvider(provider: AiProvider): List<AiModel> {
        return when (provider) {
            AiProvider.QWEN -> listOf(
                AiModel("qwen-turbo", "Qwen Turbo", AiProvider.QWEN, "速度快，成本低"),
                AiModel("qwen-plus", "Qwen Plus", AiProvider.QWEN, "均衡，能力强"),
                AiModel("qwen-max", "Qwen Max", AiProvider.QWEN, "推理能力最强"),
                AiModel("qwen-flash", "Qwen Flash", AiProvider.QWEN, "极速，适用于简单任务") // 用户特别提到的
            )
            AiProvider.DOUBAO -> listOf(
                AiModel("doubao-pro-4k", "Doubao Pro 4k", AiProvider.DOUBAO),
                AiModel("doubao-lite-4k", "Doubao Lite 4k", AiProvider.DOUBAO)
                // 注意：豆包的模型 ID 通常需要在火山引擎控制台创建 Endpoint ID，这里仅为示例，实际需填 Endpoint ID
            )
            AiProvider.MOONSHOT -> listOf(
                AiModel("moonshot-v1-8k", "Moonshot V1 8k", AiProvider.MOONSHOT, "Kimi 8k上下文"),
                AiModel("moonshot-v1-32k", "Moonshot V1 32k", AiProvider.MOONSHOT, "Kimi 32k上下文"),
                AiModel("moonshot-v1-128k", "Moonshot V1 128k", AiProvider.MOONSHOT, "Kimi 128k上下文")
            )
            AiProvider.GEMINI -> listOf(
                AiModel("gemini-1.5-flash", "Gemini 1.5 Flash", AiProvider.GEMINI, "快速高性价比"),
                AiModel("gemini-1.5-pro", "Gemini 1.5 Pro", AiProvider.GEMINI, "最强推理能力")
            )
            AiProvider.OPENAI -> listOf(
                AiModel("gpt-3.5-turbo", "GPT-3.5 Turbo", AiProvider.OPENAI),
                AiModel("gpt-4-turbo", "GPT-4 Turbo", AiProvider.OPENAI),
                AiModel("gpt-4o", "GPT-4o", AiProvider.OPENAI, "最新旗舰")
            )
            AiProvider.DEEPSEEK -> listOf(
                AiModel("deepseek-chat", "DeepSeek Chat", AiProvider.DEEPSEEK),
                AiModel("deepseek-coder", "DeepSeek Coder", AiProvider.DEEPSEEK)
            )
        }
    }
}
