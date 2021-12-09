package com.example.data.dao.tea

import com.example.data.entities.coffee.CoffeeDraft
import com.example.data.entities.coffee.CoffeeEntity
import com.example.data.entities.tea.TeaDraft
import com.example.data.entities.tea.TeaEntity

interface TeaDao {
    suspend fun getTea(): List<TeaEntity>
    suspend fun getTeaId(id: Int): TeaEntity?
    suspend fun addTea(teaDraft: TeaDraft): TeaEntity
    suspend fun removeTea(id: Int): Boolean
    suspend fun updateTea(id: Int, teaDraft: TeaDraft)
}