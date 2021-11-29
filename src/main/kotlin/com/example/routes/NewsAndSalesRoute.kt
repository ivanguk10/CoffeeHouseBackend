package com.example.routes

import com.example.data.entities.NewsAndSalesDraft
import com.example.repository.newsAndSales.NewsAndSalesRepository
import com.example.repository.newsAndSales.NewsAndSalesRepositoryImpl
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

val newsRepository: NewsAndSalesRepository = NewsAndSalesRepositoryImpl()

fun Route.getNews() {
    get("/news") {
        call.respond(
            HttpStatusCode.OK,
            newsRepository.getNews()
        )
    }
}

fun Route.getNewsId() {
    get("/news/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@get
        }

        val news = newsRepository.getNewsId(id)

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

fun Route.postNews() {
    post("/news") {
        val newsDraft = call.receive<NewsAndSalesDraft>()
        val news = newsRepository.addNews(newsDraft)
        call.respond(news)
    }
}

fun Route.putNews() {
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
        val updatedNews = newsRepository.updateNews(newsId, newsDraft)

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

fun Route.deleteNews() {
    delete("/news/{id}") {
        val newsId = call.parameters["id"]?.toIntOrNull()

        if (newsId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@delete
        }
        val removed = newsRepository.removeNews(newsId)
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