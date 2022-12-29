package com.assignment.di.module

import com.assignment.ui.viewmodel.AssignmentDetailsViewModel
import com.assignment.ui.viewmodel.AssignmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
    AssignmentViewModel(get())

    }
   viewModel {
    AssignmentDetailsViewModel(get())

    }

}