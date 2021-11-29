package com.example.di

import com.example.data.CoffeeDao
import com.example.data.CoffeeDaoImpl
import com.example.repository.coffee.CoffeeRepository
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("coffee_house_database")
    }
    single<CoffeeDao> {
        CoffeeDaoImpl(get())
    }
    single {
        CoffeeRepository(get())
    }

}