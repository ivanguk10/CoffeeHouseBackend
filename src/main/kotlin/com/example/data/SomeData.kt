package com.example.data

import kotlinx.serialization.Serializable

@Serializable
data class SomeData(
    val id: Int,
    val name: String
)
