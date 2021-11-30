package com.example.data.entities.newsAndSales

import kotlinx.serialization.Serializable

@Serializable
data class NewsAndSalesDraft(
    var title: String,
    var description: String
)
