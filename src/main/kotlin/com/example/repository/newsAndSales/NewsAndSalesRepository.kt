package com.example.repository.newsAndSales

import com.example.data.entities.NewsAndSalesDraft
import com.example.data.entities.NewsAndSalesEntity

interface NewsAndSalesRepository {
    fun getNews(): List<NewsAndSalesEntity>
    fun getNewsId(id: Int): NewsAndSalesEntity?
    fun addNews(newsDraft: NewsAndSalesDraft): NewsAndSalesEntity
    fun removeNews(id: Int): Boolean
    fun updateNews(id: Int, newsDraft: NewsAndSalesDraft): Boolean
}