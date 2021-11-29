package com.example.data

import com.example.data.entities.CoffeeDraft
import com.example.data.entities.CoffeeEntity

interface CoffeeDao {
    suspend fun getCoffee(): List<CoffeeEntity>
    suspend fun getCoffeeId(id: Int): CoffeeEntity?
    suspend fun addCoffee(coffeeDraft: CoffeeDraft): CoffeeEntity
    suspend fun removeCoffee(id: Int): Boolean
    suspend fun updateCoffee(id: Int, coffeeDraft: CoffeeDraft)
}