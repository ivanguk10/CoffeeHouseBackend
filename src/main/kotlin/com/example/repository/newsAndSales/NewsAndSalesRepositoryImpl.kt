package com.example.repository.newsAndSales

import com.example.data.entities.NewsAndSalesDraft
import com.example.data.entities.NewsAndSalesEntity

class NewsAndSalesRepositoryImpl: NewsAndSalesRepository {

    private val newsAndSales = mutableListOf(
        NewsAndSalesEntity(1, "Happy Monday", "Free cappuccino"),
        NewsAndSalesEntity(2, "Happy Tuesday", "Free latte")
    )

    override fun getNews(): List<NewsAndSalesEntity> {
        return newsAndSales
    }

    override fun getNewsId(id: Int): NewsAndSalesEntity? {
        return newsAndSales.firstOrNull{it.id == id}
    }

    override fun addNews(newsDraft: NewsAndSalesDraft): NewsAndSalesEntity {
        val newNews = NewsAndSalesEntity(
            newsAndSales.size + 1,
            newsDraft.title,
            newsDraft.description
        )
        newsAndSales.add(newNews)

        return newNews
    }

    override fun removeNews(id: Int): Boolean {
        return newsAndSales.removeIf { it.id == id }
    }

    override fun updateNews(id: Int, newsDraft: NewsAndSalesDraft): Boolean {
        val newsToUpdate = newsAndSales.firstOrNull { it.id == id }
            ?: return false

        newsToUpdate.title = newsDraft.title
        newsToUpdate.description = newsDraft.description

        return true
    }
}