package com.example.data.dao.newsAndSales

import com.example.data.entities.newsAndSales.NewsAndSalesDraft
import com.example.data.entities.newsAndSales.NewsAndSalesEntity

interface NewsAndSalesDao {
    suspend fun getNews(): List<NewsAndSalesEntity>
    suspend fun getNewsId(id: Int): NewsAndSalesEntity?
    suspend fun addNews(newsAndSalesDraft: NewsAndSalesDraft): NewsAndSalesEntity
    suspend fun removeNews(id: Int): Boolean
    suspend fun updateNews(id: Int, newsAndSalesDraft: NewsAndSalesDraft)
}