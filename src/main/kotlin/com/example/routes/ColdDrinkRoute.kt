package com.example.routes

import com.example.data.entities.colddrinks.ColdDrinkDraft
import com.example.data.entities.tea.TeaDraft
import com.example.repository.colddrinks.ColdDrinkRepository
import com.example.repository.tea.TeaRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getDrink(coldDrinkRepository: ColdDrinkRepository) {
    get("/drinks") {
        call.respond(
            HttpStatusCode.OK,
            coldDrinkRepository.getDrink()
        )
    }
}

fun Route.getDrinkId(coldDrinkRepository: ColdDrinkRepository) {
    get("/drinks/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@get
        }
        val drink = coldDrinkRepository.getDrinkId(id)

        if (drink == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "No tea with id = $id"
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                drink
            )
        }
    }
}

fun Route.postDrink(coldDrinkRepository: ColdDrinkRepository) {
    post("/drinks") {
        val drinkDraft = call.receive<ColdDrinkDraft>()
        val drink = coldDrinkRepository.addDrink(drinkDraft)
        call.respond(drink)
    }
}

fun Route.putDrink(coldDrinkRepository: ColdDrinkRepository) {
    put("/drinks/{id}") {
        val drinkId = call.parameters["id"]?.toIntOrNull()
        val drinkDraft = call.receive<ColdDrinkDraft>()

        if (drinkId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@put
        }

        val updatedDrink = coldDrinkRepository.updateDrink(drinkId, drinkDraft)

        if (updatedDrink) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "No tea with id = $drinkId"
            )
        }
    }
}

fun Route.deleteDrink(coldDrinkRepository: ColdDrinkRepository) {
    delete("/drinks/{id}") {
        val drinkId = call.parameters["id"]?.toIntOrNull()

        if (drinkId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@delete
        }
        val removedDrink = coldDrinkRepository.removeDrink(drinkId)
        if (removedDrink) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "No tea with id = $drinkId"
            )
        }
    }
}