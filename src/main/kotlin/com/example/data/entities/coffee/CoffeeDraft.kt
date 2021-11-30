package com.example.data.entities.coffee

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeDraft(
    val name: String,
    var price: Float
)
