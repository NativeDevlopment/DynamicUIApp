package com.network.repository

import android.graphics.Bitmap
import com.network.common.ResultState
import com.network.dto.DynamicUiData
import kotlinx.coroutines.flow.Flow

interface AssignmentRepository {
   fun fetchCustomUi(url : String): Flow<ResultState<DynamicUiData>>
   fun fetchImage(url :String) :Flow<ResultState<Bitmap>>
}