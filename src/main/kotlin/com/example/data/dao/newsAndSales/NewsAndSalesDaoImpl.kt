package com.example.data.dao.newsAndSales

import com.example.data.entities.newsAndSales.NewsAndSalesDraft
import com.example.data.entities.newsAndSales.NewsAndSalesEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class NewsAndSalesDaoImpl(
    private val db: CoroutineDatabase
): NewsAndSalesDao {

    private val newsAndSales = db.getCollection<NewsAndSalesEntity>()

    override suspend fun getNews(): List<NewsAndSalesEntity> {
        return newsAndSales.find()
            .descendingSort(NewsAndSalesEntity::id)
            .toList()
    }

    override suspend fun getNewsId(id: Int): NewsAndSalesEntity? {
        return newsAndSales.findOne(Filters.eq("id", id))
    }

    override suspend fun addNews(newsAndSalesDraft: NewsAndSalesDraft): NewsAndSalesEntity {
        val newsEntity = NewsAndSalesEntity(
            newsAndSales.find()
                .toList().size + 1,
            newsAndSalesDraft.title,
            newsAndSalesDraft.description
        )

        newsAndSales.insertOne(
            newsEntity
        )
        return newsEntity
    }

    override suspend fun removeNews(id: Int): Boolean {
        val newsEntity = newsAndSales.findOne(Filters.eq("id", id))
        return if (newsEntity != null) {
            newsAndSales.deleteOne(Filters.eq("id", id))
            true
        } else {
            false
        }
    }

    override suspend fun updateNews(id: Int, newsAndSalesDraft: NewsAndSalesDraft) {
        val newsEntity = newsAndSales.findOne(Filters.eq("id", id))
        newsAndSales.findOneAndUpdate(
            Filters.eq("id", id),
            Filters.eq("title", newsAndSalesDraft.title)
        )

    }

}