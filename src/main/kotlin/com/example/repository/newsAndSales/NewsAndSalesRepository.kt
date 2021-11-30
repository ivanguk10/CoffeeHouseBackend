package com.example.repository.newsAndSales

import com.example.data.dao.newsAndSales.NewsAndSalesDao
import com.example.data.entities.coffee.CoffeeDraft
import com.example.data.entities.coffee.CoffeeEntity
import com.example.data.entities.newsAndSales.NewsAndSalesDraft
import com.example.data.entities.newsAndSales.NewsAndSalesEntity

class NewsAndSalesRepository(private val newsAndSalesDao: NewsAndSalesDao) {

    suspend fun getNews(): List<NewsAndSalesEntity> {
        return newsAndSalesDao.getNews()
    }

    suspend fun getNewsId(id: Int): NewsAndSalesEntity? {
        return newsAndSalesDao.getNewsId(id)
    }

    suspend fun addNews(newsAndSalesDraft: NewsAndSalesDraft): NewsAndSalesEntity {
        return newsAndSalesDao.addNews(newsAndSalesDraft)
    }

    suspend fun removeNews(id: Int): Boolean {
        return newsAndSalesDao.removeNews(id)
    }

    suspend fun updateNews(id: Int, newsAndSalesDraft: NewsAndSalesDraft): Boolean {
        newsAndSalesDao.updateNews(id, newsAndSalesDraft)
        return true
    }
}