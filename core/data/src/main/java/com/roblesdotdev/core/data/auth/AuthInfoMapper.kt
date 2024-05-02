package com.roblesdotdev.core.data.auth

import com.roblesdotdev.core.domain.AuthInfo

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        userId = this.userId
    )
}

fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        userId = this.userId
    )
}