package com.roblesdotdev.run.domain

import com.roblesdotdev.core.domain.location.LocationWithAltitude
import kotlinx.coroutines.flow.Flow

interface LocationObserver {
    fun observeLocation(interval: Long) : Flow<LocationWithAltitude>
}