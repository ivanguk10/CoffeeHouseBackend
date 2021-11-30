package com.example.repository.coffee

import com.example.data.dao.coffee.CoffeeDao
import com.example.data.entities.coffee.CoffeeDraft
import com.example.data.entities.coffee.CoffeeEntity

class CoffeeRepository(private val coffeeDao: CoffeeDao) {

    suspend fun getCoffee(): List<CoffeeEntity> {
        return coffeeDao.getCoffee()
    }

    suspend fun getCoffeeId(id: Int): CoffeeEntity? {
        return coffeeDao.getCoffeeId(id)
    }

    suspend fun addCoffee(coffeeDraft: CoffeeDraft): CoffeeEntity {
        return coffeeDao.addCoffee(coffeeDraft)
    }

    suspend fun removeCoffee(id: Int): Boolean {
        return coffeeDao.removeCoffee(id)
    }

    suspend fun updateCoffee(id: Int, coffeeDraft: CoffeeDraft): Boolean {
//        val coffeeToUpdate = coffeeList.firstOrNull { it.id == id }
//            ?: return false
//
//        coffeeToUpdate.name = coffeeDraft.name
//        coffeeToUpdate.price = coffeeDraft.price
//
//        return true
        coffeeDao.updateCoffee(id, coffeeDraft)
        return true
    }
}