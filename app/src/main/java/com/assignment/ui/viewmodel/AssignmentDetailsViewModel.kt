package com.assignment.ui.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.network.repository.AssignmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssignmentDetailsViewModel(val assinmentRepository: AssignmentRepository) : ViewModel() {

    var  image = mutableStateOf<Bitmap?>(null)





    fun fetchImage(url :String){
        viewModelScope.launch  (Dispatchers.IO){

            assinmentRepository.fetchImage(url).collect{imageData->
                image.value=imageData.data

            }

        }

    }
}