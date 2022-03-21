package by.godevelopment.fourthtask.data

import by.godevelopment.fourthtask.commons.CITY_KEY
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val bankApi: BankApi
) {
    suspend fun getAllData() = bankApi.getAllDataByCity(CITY_KEY)
}