package com.assignment.ui.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.util.Constants
import com.network.common.ResultState
import com.network.dto.DynamicUiData
import com.network.repository.AssignmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssignmentViewModel (val assinmentRepository: AssignmentRepository) :ViewModel() {
    val dynamicUiData = MutableLiveData<ResultState<DynamicUiData>>()

    var  image = mutableStateOf<Bitmap?>(null)

    fun getCustomUiData() {
        viewModelScope.launch(Dispatchers.IO) {
            assinmentRepository.fetchCustomUi(Constants.customUiUrl).collect{
                dynamicUiData.postValue(it)

            }

        }


    }
    fun fetchImage(url :String){
        viewModelScope.launch  (Dispatchers.IO){

                assinmentRepository.fetchImage(url).collect{imageData->
                    image.value=imageData.data

            }

        }

    }
}