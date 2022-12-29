package com.assignment.di.module

import com.network.api.IAssignmentApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module{
    single {
        get<Retrofit>().create(IAssignmentApi::class.java)
    }
}