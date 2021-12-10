package com.example.data.entities.colddrinks

import kotlinx.serialization.Serializable

@Serializable
data class ColdDrinkDraft(
    val name: String,
    var price: Float
)
