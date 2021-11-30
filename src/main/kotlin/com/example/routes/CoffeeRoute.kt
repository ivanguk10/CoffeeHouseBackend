package com.example.routes

import com.example.data.entities.coffee.CoffeeDraft
import com.example.repository.coffee.CoffeeRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getCoffee(coffeeRepository: CoffeeRepository) {
    get("/coffee") {
        call.respond(
            HttpStatusCode.OK,
            coffeeRepository.getCoffee()
        )
    }
}

fun Route.getCoffeeId(coffeeRepository: CoffeeRepository) {
    get("/coffee/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@get
        }
        val coffee = coffeeRepository.getCoffeeId(id)

        if (coffee == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "No coffee with id = $id"
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                coffee
            )
        }
    }
}

fun Route.postCoffee(coffeeRepository: CoffeeRepository) {
    post("/coffee") {
        val coffeeDraft = call.receive<CoffeeDraft>()
        val coffee = coffeeRepository.addCoffee(coffeeDraft)
        call.respond(coffee)
    }
}

fun Route.putCoffee(coffeeRepository: CoffeeRepository) {
    put("/coffee/{id}") {
        val coffeeId = call.parameters["id"]?.toIntOrNull()
        val coffeeDraft = call.receive<CoffeeDraft>()

        if (coffeeId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@put
        }

        val updatedCoffee = coffeeRepository.updateCoffee(coffeeId, coffeeDraft)

        if (updatedCoffee) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "No news with id = $coffeeId"
            )
        }
    }
}

fun Route.deleteCoffee(coffeeRepository: CoffeeRepository) {
    delete("/coffee/{id}") {
        val coffeeId = call.parameters["id"]?.toIntOrNull()

        if (coffeeId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@delete
        }
        val removedCoffee = coffeeRepository.removeCoffee(coffeeId)
        if (removedCoffee) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "No news with id = $coffeeId"
            )
        }
    }
}