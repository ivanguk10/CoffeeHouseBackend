package com.example.repository.colddrinks

import com.example.data.dao.colddrinks.ColdDrinksDao
import com.example.data.entities.colddrinks.ColdDrinkDraft
import com.example.data.entities.colddrinks.ColdDrinkEntity
import com.example.data.entities.tea.TeaDraft
import com.example.data.entities.tea.TeaEntity

class ColdDrinkRepository(private val coldDrinksDao: ColdDrinksDao) {
    suspend fun getDrink(): List<ColdDrinkEntity> {
        return coldDrinksDao.getDrink()
    }

    suspend fun getDrinkId(id: Int): ColdDrinkEntity? {
        return coldDrinksDao.getDrinkId(id)
    }

    suspend fun addDrink(coldDrinkDraft: ColdDrinkDraft): ColdDrinkEntity {
        return coldDrinksDao.addDrink(coldDrinkDraft)
    }

    suspend fun removeDrink(id: Int): Boolean {
        return coldDrinksDao.removeDrink(id)
    }

    suspend fun updateDrink(id: Int, coldDrinkDraft: ColdDrinkDraft): Boolean {
        coldDrinksDao.updateDrink(id, coldDrinkDraft)
        return true
    }
}