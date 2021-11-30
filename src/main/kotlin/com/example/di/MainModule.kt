package com.example.di

import com.example.data.dao.coffee.CoffeeDao
import com.example.data.dao.coffee.CoffeeDaoImpl
import com.example.data.dao.newsAndSales.NewsAndSalesDao
import com.example.data.dao.newsAndSales.NewsAndSalesDaoImpl
import com.example.repository.coffee.CoffeeRepository
import com.example.repository.newsAndSales.NewsAndSalesRepository
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

    single<NewsAndSalesDao> {
        NewsAndSalesDaoImpl(get())
    }
    single {
        NewsAndSalesRepository(get())
    }

}