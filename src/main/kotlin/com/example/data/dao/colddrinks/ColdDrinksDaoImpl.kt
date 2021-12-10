package com.example.data.dao.colddrinks

import com.example.data.entities.coffee.CoffeeDraft
import com.example.data.entities.coffee.CoffeeEntity
import com.example.data.entities.colddrinks.ColdDrinkDraft
import com.example.data.entities.colddrinks.ColdDrinkEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class ColdDrinksDaoImpl(
    private val db: CoroutineDatabase
): ColdDrinksDao {
    private val drinks = db.getCollection<ColdDrinkEntity>()

    override suspend fun getDrink(): List<ColdDrinkEntity> {
        return drinks.find()
            .descendingSort(CoffeeEntity::id)
            .toList()
    }

    override suspend fun getDrinkId(id: Int): ColdDrinkEntity? {
        return drinks.findOne(Filters.eq("id", id))
    }

    override suspend fun addDrink(coldDrinkDraft: ColdDrinkDraft): ColdDrinkEntity {
        val coldDrinkEntity = ColdDrinkEntity(
            drinks.find()
                .toList().size + 1,
            coldDrinkDraft.name,
            coldDrinkDraft.price
        )

        drinks.insertOne(
            coldDrinkEntity
        )
        return coldDrinkEntity
    }

    override suspend fun removeDrink(id: Int): Boolean {
        val coldDrinkEntity = drinks.findOne(Filters.eq("id", id))
        return if (coldDrinkEntity != null) {
            drinks.deleteOne(Filters.eq("id", id))
            true
        } else {
            false
        }
    }

    override suspend fun updateDrink(id: Int, coldDrinkDraft: ColdDrinkDraft) {
        val coldDrinkEntity = drinks.findOne(Filters.eq("id", id))
//        drinks.findOneAndUpdate(Filters.eq("id", id), Filters.eq("name", coffeeDraft.name)  )
        drinks.findOneAndUpdate(
            Filters.eq("id", id),
            Filters.eq("price", coldDrinkDraft.price)
        )
    }
}