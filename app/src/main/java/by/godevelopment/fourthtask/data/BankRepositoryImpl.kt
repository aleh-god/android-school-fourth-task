package by.godevelopment.fourthtask.data

import by.godevelopment.fourthtask.domain.BankRepository
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BankRepository {
    override suspend fun getAllData() = remoteDataSource.getAllData()
}