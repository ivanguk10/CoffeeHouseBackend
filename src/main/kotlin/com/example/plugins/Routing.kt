package com.example.plugins

import com.example.repository.coffee.CoffeeRepository
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

    routing {
        getCoffee(coffeeRepository)
        getCoffeeId(coffeeRepository)
        postCoffee(coffeeRepository)
        putCoffee(coffeeRepository)
        deleteCoffee(coffeeRepository)


        getNews()
        getNewsId()
        get("/") {
                call.respondText("Hello World!")
            }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
