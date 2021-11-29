package com.example.data

import com.example.data.entities.CoffeeDraft
import com.example.data.entities.CoffeeEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.where

class CoffeeDaoImpl(
    private val db: CoroutineDatabase
): CoffeeDao {

    private val coffee = db.getCollection<CoffeeEntity>()

    override suspend fun getCoffee(): List<CoffeeEntity> {
        return coffee.find()
            .descendingSort(CoffeeEntity::id)
            .toList()
    }

    override suspend fun getCoffeeId(id: Int): CoffeeEntity? {
        return coffee.findOne(Filters.eq("id", id))
    }

    override suspend fun addCoffee(coffeeDraft: CoffeeDraft): CoffeeEntity {
        val coffeeEntity = CoffeeEntity(
            coffee.find()
                .toList().size + 1,
            coffeeDraft.name,
            coffeeDraft.price
        )

        coffee.insertOne(
            coffeeEntity
        )
        return coffeeEntity
    }

    override suspend fun removeCoffee(id: Int): Boolean {
        val coffeeEntity = coffee.findOne(Filters.eq("id", id))
        return if (coffeeEntity != null) {
            coffee.deleteOne(Filters.eq("id", id))
            true
        } else {
            false
        }
    }

    override suspend fun updateCoffee(id: Int, coffeeDraft: CoffeeDraft) {
        val coffeeEntity = coffee.findOne(Filters.eq("id", id))
//        coffee.findOneAndUpdate(Filters.eq("id", id), Filters.eq("name", coffeeDraft.name)  )
        coffee.findOneAndUpdate(
            Filters.eq("id", id),
            Filters.eq("price", coffeeDraft.price)
        )

    }
}