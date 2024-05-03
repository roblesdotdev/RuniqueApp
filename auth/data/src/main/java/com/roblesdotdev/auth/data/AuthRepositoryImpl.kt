package com.roblesdotdev.auth.data

import com.roblesdotdev.auth.domain.AuthRepository
import com.roblesdotdev.core.data.networking.post
import com.roblesdotdev.core.domain.AuthInfo
import com.roblesdotdev.core.domain.SessionStorage
import com.roblesdotdev.core.domain.util.DataError
import com.roblesdotdev.core.domain.util.EmptyResult
import com.roblesdotdev.core.domain.util.Result
import com.roblesdotdev.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage,
) : AuthRepository {
    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email = email,
                password = password,
            )
        )

        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }

        return result.asEmptyDataResult()
    }

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