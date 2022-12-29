package com.assignment.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import com.assignment.ui.components.MainContent
import com.assignment.ui.entity.DynamicUiEntity
import com.assignment.ui.theme.AssignmentTheme
import com.assignment.ui.viewmodel.AssignmentDetailsViewModel
import com.assignment.util.Constants
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : ComponentActivity() {
    private val assignmentDetailsViewModel:AssignmentDetailsViewModel by viewModel()
    lateinit var dynamicUiData: DynamicUiEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var data = ""

        intent?.let {
            intent.getStringExtra(Constants.NAVIGATE_DATA)?.let {
                data = it
            }
            dynamicUiData = Gson().fromJson(data, DynamicUiEntity::class.java)
        }
        setContent {
            AssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(key1 = assignmentDetailsViewModel.image.value, block = {
                        setContent {
                            dynamicUiData.logoUrl?.let { assignmentDetailsViewModel.fetchImage(it) }
                            MainContent(data = dynamicUiData, onFetchImage = {
                                assignmentDetailsViewModel.image.value?.asImageBitmap()
                            }, onNavigate = {

                            }, isShowDetails = true)
                        }
                    })

                }
            }
        }
    }
}

