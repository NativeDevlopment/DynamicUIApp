package com.network.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.network.api.IAssignmentApi
import com.network.common.ResultState
import com.network.dto.DynamicUiData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.UnknownHostException


class AssginmentRepositoryImpl  (private val api: IAssignmentApi): AssignmentRepository {
    override fun fetchCustomUi(url: String): Flow<ResultState<DynamicUiData>> {
        return flow {
            emit(ResultState.loading(null))
            emit( ResultState.success( remoteFetchCustomUIApiCall(url)))
        }.catch { e ->
            when (e) {
                is SocketException -> {
                    emit(
                        ResultState.error(
                            "Request timeout. Please check your internet connection.",
                            null
                        )
                    )
                }
                is HttpException -> {
                    val httpCode = e.code()
                    emit(
                        ResultState.error(
                             "Oops..! Something went wrong!\nPlease try again after sometime.\n(Error Code: $httpCode)"
                            ,
                            null
                        )
                    )
                }
                is UnknownHostException, is IOException -> {
                    emit(
                        ResultState.error(
                            "Unable to reach server. Please check your internet connection.",
                            null
                        )
                    )
                }
                else -> {
                    emit(
                        ResultState.error(
                            "Unable to understand server's response. Please try again after sometime.",
                            null
                        )
                    )
                }
            }
        }
    }


    override fun fetchImage(url :String): Flow<ResultState<Bitmap>> {
        return flow {
            emit(ResultState.loading(null))
            emit( ResultState.success( remoteFetchImage(url)))
        }.catch { e ->
            when (e) {
                is SocketException -> {
                    emit(
                        ResultState.error(
                            "Request timeout. Please check your internet connection.",
                            null
                        )
                    )
                }
                is HttpException -> {
                    val httpCode = e.code()
                    emit(
                        ResultState.error(
                            "Oops..! Something went wrong!\nPlease try again after sometime.\n(Error Code: $httpCode)"
                            ,
                            null
                        )
                    )
                }
                is UnknownHostException, is IOException -> {
                    emit(
                        ResultState.error(
                            "Unable to reach server. Please check your internet connection.",
                            null
                        )
                    )
                }
                else -> {
                    emit(
                        ResultState.error(
                            "Unable to understand server's response. Please try again after sometime.",
                            null
                        )
                    )
                }
            }
        }
    }

 private suspend fun  remoteFetchCustomUIApiCall(url :String) : DynamicUiData{
  return  api.fetchCustomUi(url);
}
   private suspend fun remoteFetchImage(url :String): Bitmap {

        val bytes = api.fetchImage(url).bytes();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

}