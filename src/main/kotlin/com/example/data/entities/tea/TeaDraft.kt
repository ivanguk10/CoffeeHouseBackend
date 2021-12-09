package com.example.data.entities.tea

import kotlinx.serialization.Serializable

@Serializable
data class TeaDraft(
    val name: String,
    var price: Float
) {
}