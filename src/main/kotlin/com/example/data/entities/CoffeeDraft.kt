package com.example.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeDraft(
    val name: String,
    var price: Float
)
