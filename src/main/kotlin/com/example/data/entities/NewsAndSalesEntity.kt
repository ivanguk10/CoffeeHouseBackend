package com.example.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class NewsAndSalesEntity(
    val id: Int,
    var title: String,
    var description: String
)
