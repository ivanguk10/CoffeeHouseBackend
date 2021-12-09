package com.example.routes

import com.example.data.entities.coffee.CoffeeDraft
import com.example.data.entities.tea.TeaDraft
import com.example.repository.coffee.CoffeeRepository
import com.example.repository.tea.TeaRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getTea(teaRepository: TeaRepository) {
    get("/tea") {
        call.respond(
            HttpStatusCode.OK,
            teaRepository.getTea()
        )
    }
}

fun Route.getTeaId(teaRepository: TeaRepository) {
    get("/tea/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@get
        }
        val tea = teaRepository.getTeaId(id)

        if (tea == null) {
            call.respond(
                HttpStatusCode.NotFound,
                "No tea with id = $id"
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                tea
            )
        }
    }
}

fun Route.postTea(teaRepository: TeaRepository) {
    post("/tea") {
        val teaDraft = call.receive<TeaDraft>()
        val tea = teaRepository.addTea(teaDraft)
        call.respond(tea)
    }
}

fun Route.putTea(teaRepository: TeaRepository) {
    put("/tea/{id}") {
        val teaId = call.parameters["id"]?.toIntOrNull()
        val teaDraft = call.receive<TeaDraft>()

        if (teaId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@put
        }

        val updatedTea = teaRepository.updateTea(teaId, teaDraft)

        if (updatedTea) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "No tea with id = $teaId"
            )
        }
    }
}

fun Route.deleteTea(teaRepository: TeaRepository) {
    delete("/tea/{id}") {
        val teaId = call.parameters["id"]?.toIntOrNull()

        if (teaId == null) {
            call.respond(
                HttpStatusCode.BadRequest,
                "Id should be Int"
            )
            return@delete
        }
        val removedTea = teaRepository.removeTea(teaId)
        if (removedTea) {
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(
                HttpStatusCode.NotFound,
                "No tea with id = $teaId"
            )
        }
    }
}