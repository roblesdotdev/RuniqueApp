package com.roblesdotdev.core.domain.util

/**
 * Define common network and local data errors.
 */
sealed interface DataError: Error {

    enum class Network: DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
    }

    enum class Local: DataError {
        DISK_FULL,
    }
}