package com.assignment.ui.entity

import com.network.dto.DynamicUiData
import com.network.dto.UiData

fun DynamicUiData.map()= DynamicUiEntity(
    logoUrl = logoUrl,
    headingText= headingText,
    uidata?.map {  it.map()  }

)

fun UiData.map()=UiDataEntity(
    key = key,
    hint=hint,
    value=value,
    uitype=uitype,
    inputData = ""
)
