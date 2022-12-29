package com.assignment.ui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.asImageBitmap
import com.assignment.ui.components.ErrorBodyContent
import com.assignment.ui.components.MainContent
import com.assignment.ui.entity.DynamicUiEntity
import com.assignment.ui.entity.map
import com.assignment.ui.viewmodel.AssignmentViewModel
import com.assignment.util.Constants
import com.google.gson.Gson
import com.network.common.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val assignmentViewModel: AssignmentViewModel by viewModel()
    lateinit var dynamicUiData: DynamicUiEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        assignmentViewModel.getCustomUiData()
        assignmentViewModel.dynamicUiData.observeForever {
            it.let {
                when (it.status) {
                    Status.LOADING->{
                        setContent { MainContent(isUpdatingContent = true, data=null , onFetchImage = {
                            null
                        }, onNavigate = {}) }
                    }

                    Status.SUCCESS -> {
                        it?.data.let { data ->
                            data?.logoUrl?.let { url -> assignmentViewModel.fetchImage(url) }

                            if (data != null) {
                                dynamicUiData=data.map()
                                setContent {

                                    MainContent( data=dynamicUiData, onFetchImage = {
                                      assignmentViewModel.image.value?.let {bitmapData->
                                          bitmapData.asImageBitmap()
                                      }
                                    }, onNavigate = { uiListData->
                                        dynamicUiData.uidata=uiListData;
                                       var validate =false;
                                        var message: String =""
                                        dynamicUiData.let {
                                            it.uidata?.forEach { uiDataEntity ->
                                                if (uiDataEntity.uitype.equals(Constants.EDIT_TEXT)) {
                                                    if (!uiDataEntity.inputData.isNullOrBlank()) {
                                                        validate = true
                                                    } else {
                                                        validate = false;
                                                        message = "Please enter " + (uiDataEntity.key?.split(
                                                                "_"
                                                            )
                                                                ?.get(1) ?: "")
                                                        return@let

                                                    }

                                                }
                                                ;
                                            }
                                        }
                                        if(validate) {
                                            val navigateToDetailsActivity =
                                                Intent(this, DetailsActivity::class.java)
                                            navigateToDetailsActivity.putExtra(
                                                Constants.NAVIGATE_DATA,
                                                Gson().toJson(dynamicUiData)
                                            )
                                            startActivity(navigateToDetailsActivity)
                                        }else{
                                            val dialogBuilder = AlertDialog.Builder(this)
                                            dialogBuilder.setMessage(message)
                                                .setCancelable(true)
                                                .setPositiveButton("Ok", DialogInterface.OnClickListener {
                                                        dialog, id ->
                                                    dialog.dismiss()

                                                })

                                            val alert = dialogBuilder.create()
                                            alert.show()

                                        }
                                    } )
                                }
                            }

                        }
                    }
                    Status.ERROR->{
                        it?.message?.let { it1 ->
                            setContent {
                                ErrorBodyContent(msg = it1, onReload = {
                                    assignmentViewModel.getCustomUiData()
                                })
                            }

                        }
                    }
                }


            }
        }

    }
}



