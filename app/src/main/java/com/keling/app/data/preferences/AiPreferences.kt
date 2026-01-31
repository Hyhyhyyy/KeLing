package com.keling.app.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.keling.app.data.model.AiConfig
import com.keling.app.data.model.AiProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.aiDataStore: DataStore<Preferences> by preferencesDataStore(name = "ai_prefs")

private val KEY_QWEN_API_KEY = stringPreferencesKey("qwen_api_key")
private val KEY_SELECTED_PROVIDER_ID = stringPreferencesKey("ai_provider_id")
private val KEY_SELECTED_MODEL_ID = stringPreferencesKey("ai_model_id")

@Singleton
class AiPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.aiDataStore

    // ... 保持原有的 apiKey 方法兼容性，或者标记过时 ...
    val apiKey: Flow<String> = dataStore.data.map { prefs ->
        prefs[KEY_QWEN_API_KEY] ?: ""
    }

    suspend fun setApiKey(value: String) {
        dataStore.edit { prefs ->
            prefs[KEY_QWEN_API_KEY] = value
        }
    }

    suspend fun clearApiKey() {
        dataStore.edit { prefs ->
            prefs.remove(KEY_QWEN_API_KEY)
        }
    }

    // 新增：选���厂商ID
    val selectedProviderId: Flow<String> = dataStore.data.map { prefs ->
        prefs[KEY_SELECTED_PROVIDER_ID] ?: AiProvider.QWEN.id // 默认千问
    }

    // 新增：选中模型ID
    val selectedModelId: Flow<String> = dataStore.data.map { prefs ->
        prefs[KEY_SELECTED_MODEL_ID] ?: "qwen-turbo" // 默认模型
    }

    suspend fun setAiConfig(providerId: String, modelId: String) {
        dataStore.edit { prefs ->
            prefs[KEY_SELECTED_PROVIDER_ID] = providerId
            prefs[KEY_SELECTED_MODEL_ID] = modelId
        }
    }

    // 获取当前完整配置（Provider Object + Model ID）用于 API 调用
    val currentAiConfig: Flow<Pair<AiProvider, String>> = dataStore.data.map { prefs ->
        val pId = prefs[KEY_SELECTED_PROVIDER_ID] ?: AiProvider.QWEN.id
        val mId = prefs[KEY_SELECTED_MODEL_ID] ?: "qwen-turbo"
        val provider = AiProvider.fromId(pId) ?: AiProvider.QWEN
        provider to mId
    }
}
