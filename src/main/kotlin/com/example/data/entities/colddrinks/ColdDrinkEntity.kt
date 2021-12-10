package com.example.data.entities.colddrinks

import kotlinx.serialization.Serializable

@Serializable
data class ColdDrinkEntity(
    val id: Int,
    var name: String,
    var price: Float
) {

}