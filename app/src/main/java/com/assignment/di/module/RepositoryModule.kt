package com.assignment.di.module


import com.network.repository.AssginmentRepositoryImpl
import com.network.repository.AssignmentRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory  <AssignmentRepository> {

        AssginmentRepositoryImpl(get())
    }

}