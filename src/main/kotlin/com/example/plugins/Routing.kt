package com.example.plugins

import com.example.repository.coffee.CoffeeRepository
import com.example.repository.colddrinks.ColdDrinkRepository
import com.example.repository.newsAndSales.NewsAndSalesRepository
import com.example.repository.tea.TeaRepository
import com.example.routes.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val coffeeRepository by inject<CoffeeRepository>()
    val newsAndSalesRepository by inject<NewsAndSalesRepository>()
    val teaRepository by inject<TeaRepository>()
    val coldDrinkRepository by inject<ColdDrinkRepository>()

    routing {
        getDrink(coldDrinkRepository)
        getDrinkId(coldDrinkRepository)
        postDrink(coldDrinkRepository)
        putDrink(coldDrinkRepository)
        deleteDrink(coldDrinkRepository)


        getTea(teaRepository)
        getTeaId(teaRepository)
        postTea(teaRepository)
        putTea(teaRepository)
        deleteTea(teaRepository)


        getCoffee(coffeeRepository)
        getCoffeeId(coffeeRepository)
        postCoffee(coffeeRepository)
        putCoffee(coffeeRepository)
        deleteCoffee(coffeeRepository)


        getNews(newsAndSalesRepository)
        getNewsId(newsAndSalesRepository)
        postNews(newsAndSalesRepository)
        putNews(newsAndSalesRepository)
        deleteNews(newsAndSalesRepository)
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
