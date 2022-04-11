package com.interview.star.di

import com.interview.star.data.remote.repository.UserRepository
import com.interview.star.data.remote.repository.impl.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}