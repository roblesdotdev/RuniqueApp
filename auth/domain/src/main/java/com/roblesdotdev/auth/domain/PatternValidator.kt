package com.roblesdotdev.auth.domain

interface PatternValidator {
    fun matches(value: String) : Boolean
}