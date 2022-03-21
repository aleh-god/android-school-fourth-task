package by.godevelopment.fourthtask.di

import by.godevelopment.fourthtask.data.BankRepositoryImpl
import by.godevelopment.fourthtask.domain.BankRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    abstract fun bindRepositoryToImpl(bankRepositoryImpl: BankRepositoryImpl) : BankRepository
}