package com.keling.app.data.network

import com.google.gson.annotations.SerializedName

// Standard OpenAI Chat Completion Request
data class OpenAiRequest(
    val model: String,
    val messages: List<Message>,
    val stream: Boolean = false,
    val temperature: Double? = 0.7
) {
    data class Message(
        val role: String,
        val content: String
    )
}

// Standard OpenAI Chat Completion Response
data class OpenAiResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>
) {
    data class Choice(
        val index: Int,
        val message: Message,
        @SerializedName("finish_reason")
        val finishReason: String?
    )

    data class Message(
        val role: String,
        val content: String
    )
}
