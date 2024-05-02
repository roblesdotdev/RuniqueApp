package com.roblesdotdev.core.data.auth

import android.content.SharedPreferences
import com.roblesdotdev.core.domain.AuthInfo
import com.roblesdotdev.core.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EncryptedSessionStorage(
    private val sharedPreferences: SharedPreferences
) : SessionStorage {
    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO) {
            val json = sharedPreferences.getString(AUTH_INFO_KEY, null)
            json?.let {
                Json.decodeFromString<AuthInfoSerializable>(it).toAuthInfo()
            }
        }
    }

    override suspend fun set(info: AuthInfo?) {
        withContext(Dispatchers.IO) {
            if (info == null) {
                sharedPreferences.edit().remove(AUTH_INFO_KEY).commit()
                return@withContext
            }

            val json = Json.encodeToString(info.toAuthInfoSerializable())
            sharedPreferences
                .edit()
                .putString(AUTH_INFO_KEY, json)
                .commit()
        }
    }

    companion object {
        private const val AUTH_INFO_KEY = "auth_info_key"
    }

}