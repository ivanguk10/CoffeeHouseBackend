package com.example.data.entities.coffee

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeEntity(
    val id: Int,
    var name: String,
    var price: Float
)
