package com.assignment.di.appcomponents

import com.assignment.di.module.*
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
     retrofitModule,
    okhttpModule, repositoryModule,
     apiModule, viewModelModule
)