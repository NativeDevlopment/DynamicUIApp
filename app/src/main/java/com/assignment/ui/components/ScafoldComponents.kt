package com.assignment.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.assignment.ui.entity.DynamicUiEntity
import com.assignment.ui.entity.UiDataEntity


@Composable
fun MainContent(
    isUpdatingContent: Boolean = false,
    data : DynamicUiEntity?=null,
    onFetchImage :()->ImageBitmap?,
    onNavigate:(List<UiDataEntity>)->Unit,
    isShowDetails:Boolean=false
) {


    if(isUpdatingContent){
        Box(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()) {
            CircularProgressIndicator(

                modifier = Modifier.width(50.dp).height(50.dp).align(Alignment.Center)
            )

        }
    }
    else {

        Scaffold(
            topBar = {
                HeaderContent(
                    appBarTitle = data?.headingText,
                    bitmap = onFetchImage()
                )
            }, content = {
                BodyContent(
                    contentBody = data,
                    paddingValues = it,
                    onNavigate = { data ->
                        onNavigate.invoke(data)
                    },
                    isShowDetails = isShowDetails
                )
            }

        )
    }
}


