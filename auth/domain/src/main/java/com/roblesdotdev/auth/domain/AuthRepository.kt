package com.roblesdotdev.auth.domain

import com.roblesdotdev.core.domain.util.DataError
import com.roblesdotdev.core.domain.util.EmptyResult

interface AuthRepository {

    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}