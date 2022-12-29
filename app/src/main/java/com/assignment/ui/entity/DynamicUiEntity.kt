package com.assignment.ui.entity

data class DynamicUiEntity(

    val logoUrl: String? ,

    val headingText: String? ,

    var uidata :List<UiDataEntity>?
)
data class UiDataEntity (
                     var uitype: String? ,

                     var value: String? ,

                     var key :String? ,

                     var hint :String? ,
                       var inputData: String ="" )

