package com.assignment.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignment.R
import com.assignment.ui.entity.DynamicUiEntity
import com.assignment.ui.entity.UiDataEntity
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode


@Composable
fun ErrorBodyContent(msg: String, onReload: () -> Unit) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_baseline_error_24),
                contentDescription = "warning",
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = msg,
                style = TextStyle(
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Button(
                modifier = Modifier.padding(16.dp),
                onClick = onReload
            ) {
                Text(text = "Reload")
            }

        }

    }

}

@Composable
fun BodyContent(
    contentBody: DynamicUiEntity? = null,
    paddingValues: PaddingValues,
    onNavigate: (List<UiDataEntity>) -> Unit,
    isShowDetails:Boolean =false

) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(paddingValues)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            contentBody?.uidata?.let {
                if (isShowDetails) {
                   VerticalFlex(it,  isShowDetails)
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {

                        itemsIndexed(it) { index: Int, data: UiDataEntity ->
                            AtomMapper(
                                isShowDetails = isShowDetails,
                                uiData = data,
                                atomBaseModifier = Modifier.padding(10.dp),
                                onClickButton = {
                                    onNavigate.invoke(contentBody.uidata!!)

                                }, updateData = {
                                    contentBody.uidata!![index].inputData = it
                                })
                        }


                    }

                }
            }


        }
    }


}
@Composable
fun VerticalFlex(data: List<UiDataEntity> ,isShowDetails:Boolean) {
Card(modifier = Modifier
    .padding(10.dp)
    .fillMaxWidth(), elevation = 5.dp,
    backgroundColor=Color.Black,
    shape= RoundedCornerShape(5.dp)
    ) {


    FlowRow(
        modifier = Modifier.padding(10.dp),
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,

        mainAxisAlignment = MainAxisAlignment.SpaceBetween,
        crossAxisAlignment = FlowCrossAxisAlignment.Center,
        mainAxisSize = SizeMode.Expand
    ) {
        data.forEach { uiData ->
            AtomMapper(
                isShowDetails = isShowDetails,
                uiData = uiData,
                atomBaseModifier = Modifier.fillMaxWidth(0.33f),
                onClickButton = {


                }, updateData = {

                })
        }
    }
}
}


@Composable
fun HeaderContent(
    appBarTitle: String? = null,
    bitmap: ImageBitmap? = null,


    ) {
    Column {
        TopAppBar(
            title = {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    bitmap?.let {
                        Image(
                            bitmap = it,
                            contentDescription = "titleimage",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                        )
                    }
                    Text(text = appBarTitle ?: "", modifier = Modifier.padding(5.dp))

                }

            },

            backgroundColor = Color.Black,
            contentColor = Color.White,
            elevation = 10.dp
        )

    }
}




