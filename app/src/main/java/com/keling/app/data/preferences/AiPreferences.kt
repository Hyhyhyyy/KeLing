package com.keling.app.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.aiDataStore: DataStore<Preferences> by preferencesDataStore(name = "ai_prefs")

private val KEY_QWEN_API_KEY = stringPreferencesKey("qwen_api_key")

@Singleton
class AiPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.aiDataStore

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
}
