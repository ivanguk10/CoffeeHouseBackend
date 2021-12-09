package com.example.data.dao.tea

import com.example.data.entities.coffee.CoffeeEntity
import com.example.data.entities.tea.TeaDraft
import com.example.data.entities.tea.TeaEntity
import com.mongodb.client.model.Filters
import org.litote.kmongo.coroutine.CoroutineDatabase

class TeaDaoImpl(
    private val db: CoroutineDatabase
): TeaDao {

    private val tea = db.getCollection<TeaEntity>()

    override suspend fun getTea(): List<TeaEntity> {
        return tea.find()
            .descendingSort(CoffeeEntity::id)
            .toList()
    }

    override suspend fun getTeaId(id: Int): TeaEntity? {
        return tea.findOne(Filters.eq("id", id))
    }

    override suspend fun addTea(teaDraft: TeaDraft): TeaEntity {
        val teaEntity = TeaEntity(
            tea.find()
                .toList().size + 1,
            teaDraft.name,
            teaDraft.price
        )

        tea.insertOne(
            teaEntity
        )
        return teaEntity
    }

    override suspend fun removeTea(id: Int): Boolean {
        val teaEntity = tea.findOne(Filters.eq("id", id))
        return if (teaEntity != null) {
            tea.deleteOne(Filters.eq("id", id))
            true
        } else {
            false
        }
    }

    override suspend fun updateTea(id: Int, teaDraft: TeaDraft) {

    }
}