package com.example.routes

import com.example.data.SomeData
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

private const val BASE_URL = "http://192.168.118.1:8100"
private val somes = listOf<SomeData>(
    SomeData(0, "Data1"),
    SomeData(1, "Data2")
)


fun Route.some(id: Int) {
    get("/some") {
        call.respond(
            HttpStatusCode.OK,
            somes.get(id)
        )
    }
}