package com.assignment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignment.ui.entity.UiDataEntity
import com.assignment.ui.viewmodel.AssignmentViewModel
import okhttp3.internal.wait
import org.koin.androidx.compose.koinViewModel

@Composable
fun AtomMapper(
    uiData: UiDataEntity,
    atomBaseModifier: Modifier,
    onClickButton: () -> Unit,
    updateData:(updatedData :String)->Unit,
    isShowDetails:Boolean = false
) {




    when (uiData.uitype) {
        "label" ->
            Text(
                text = checkNotNull(uiData.value),
                color = if(isShowDetails) Color.White else MaterialTheme.colors.primary,
                fontSize = 16.sp,
                modifier = atomBaseModifier,

                )
        "edittext" -> {
            if (isShowDetails) {
                Text(

                    text = checkNotNull(uiData.inputData),
                    color = if(isShowDetails) Color.White else MaterialTheme.colors.primary,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(0.62f)


                )
            } else {
                var data by remember {

                    mutableStateOf(uiData.inputData)
                }

                OutlinedTextField(
                    value = data, onValueChange = {
                        data = it
                        updateData(data)

                    }, singleLine = true,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    label = { Text(checkNotNull(uiData.hint), color = Color.Gray) },
                    isError = uiData.inputData.isNullOrBlank(),
                    keyboardOptions = if (uiData.key.equals("text_phone"))
                        KeyboardOptions(keyboardType = KeyboardType.Phone) else
                        KeyboardOptions(keyboardType = KeyboardType.Text)
                )


            }
        }
        "button" ->
            if (isShowDetails) {
            } else {
                Button(

                    modifier = Modifier
                        .fillMaxWidth()
                        .size(65.dp)
                        .padding(5.dp, 10.dp, 5.dp, 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black, contentColor = Color.White
                    ),
                    onClick = {
                        onClickButton()
                    },
                ) {
                    Text(text = checkNotNull(uiData.value), fontSize = 16.sp

                        )
                }
            }


    }
}



