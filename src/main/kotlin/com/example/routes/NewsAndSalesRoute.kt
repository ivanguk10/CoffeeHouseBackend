package com.example.routes

import com.example.data.entities.newsAndSales.NewsAndSalesDraft
import com.example.repository.newsAndSales.NewsAndSalesRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.getNews(newsAndSalesRepository: NewsAndSalesRepository) {
    get("/news") {
        call.respond(
            HttpStatusCode.OK,
            newsAndSalesRepository.getNews()
        )
    }
}

fun Route.getNewsId(newsAndSalesRepository: NewsAndSalesRepository) {
    get("/news/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@get
        }

        val news = newsAndSalesRepository.getNewsId(id)

        if (news == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "No news with id = $id"
            )
        }else {
            call.respond(
                HttpStatusCode.OK,
                news
            )
        }
    }
}

fun Route.postNews(newsAndSalesRepository: NewsAndSalesRepository) {
    post("/news") {
        val newsDraft = call.receive<NewsAndSalesDraft>()
        val news = newsAndSalesRepository.addNews(newsDraft)
        call.respond(news)
    }
}

fun Route.putNews(newsAndSalesRepository: NewsAndSalesRepository) {
    put("/news/{id}") {
        val newsId = call.parameters["id"]?.toIntOrNull()
        val newsDraft = call.receive<NewsAndSalesDraft>()

        if (newsId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@put
        }
        val updatedNews = newsAndSalesRepository.updateNews(newsId, newsDraft)

        if (updatedNews) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "No news with id = $newsId"
            )
        }
    }
}

fun Route.deleteNews(newsAndSalesRepository: NewsAndSalesRepository) {
    delete("/news/{id}") {
        val newsId = call.parameters["id"]?.toIntOrNull()

        if (newsId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@delete
        }
        val removed = newsAndSalesRepository.removeNews(newsId)
        if (removed) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "No news with id = $newsId"
            )
        }
    }
}