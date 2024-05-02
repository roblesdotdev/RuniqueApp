package com.roblesdotdev.core.domain

interface SessionStorage {
    suspend fun get(): AuthInfo?

    suspend fun set(info: AuthInfo?)
}