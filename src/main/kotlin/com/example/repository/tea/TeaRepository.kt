package com.example.repository.tea

import com.example.data.dao.tea.TeaDao
import com.example.data.entities.tea.TeaDraft
import com.example.data.entities.tea.TeaEntity

class TeaRepository(private val teaDao: TeaDao) {

    suspend fun getTea(): List<TeaEntity> {
        return teaDao.getTea()
    }

    suspend fun getTeaId(id: Int): TeaEntity? {
        return teaDao.getTeaId(id)
    }

    suspend fun addTea(teaDraft: TeaDraft): TeaEntity {
        return teaDao.addTea(teaDraft)
    }

    suspend fun removeTea(id: Int): Boolean {
        return teaDao.removeTea(id)
    }

    suspend fun updateTea(id: Int, teaDraft: TeaDraft): Boolean {
//        val coffeeToUpdate = coffeeList.firstOrNull { it.id == id }
//            ?: return false
//
//        coffeeToUpdate.name = coffeeDraft.name
//        coffeeToUpdate.price = coffeeDraft.price
//
//        return true
        teaDao.updateTea(id, teaDraft)
        return true
    }

}