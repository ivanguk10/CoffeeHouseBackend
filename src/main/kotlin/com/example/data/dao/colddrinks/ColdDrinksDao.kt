package com.example.data.dao.colddrinks

import com.example.data.entities.coffee.CoffeeDraft
import com.example.data.entities.coffee.CoffeeEntity
import com.example.data.entities.colddrinks.ColdDrinkDraft
import com.example.data.entities.colddrinks.ColdDrinkEntity

interface ColdDrinksDao {
    suspend fun getDrink(): List<ColdDrinkEntity>
    suspend fun getDrinkId(id: Int): ColdDrinkEntity?
    suspend fun addDrink(coldDrinkDraft: ColdDrinkDraft): ColdDrinkEntity
    suspend fun removeDrink(id: Int): Boolean
    suspend fun updateDrink(id: Int, coldDrinkDraft: ColdDrinkDraft)
}