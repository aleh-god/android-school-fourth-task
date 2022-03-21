package by.godevelopment.fourthtask.domain

import by.godevelopment.fourthtask.data.dataentity.DataModel

interface BankRepository {

    suspend fun getAllData(): DataModel
}