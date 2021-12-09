package com.example.data.entities.tea

import kotlinx.serialization.Serializable

@Serializable
data class TeaEntity(
    val id: Int,
    var name: String,
    var price: Float
) {
}