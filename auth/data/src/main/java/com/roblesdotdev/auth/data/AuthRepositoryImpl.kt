package com.roblesdotdev.auth.data

import com.roblesdotdev.auth.domain.AuthRepository
import com.roblesdotdev.core.data.networking.post
import com.roblesdotdev.core.domain.util.DataError
import com.roblesdotdev.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
) : AuthRepository {
    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password,
            )
        )
    }
}