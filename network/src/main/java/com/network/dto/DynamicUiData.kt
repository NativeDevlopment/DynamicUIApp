package com.network.dto

import com.google.gson.annotations.SerializedName

data class DynamicUiData(
    @SerializedName("logo-url")
    val logoUrl: String? ,
    @SerializedName("heading-text")
    val headingText: String? ,
    @SerializedName("uidata")
    val uidata :List<UiData>?
    )
data class UiData (  @SerializedName("uitype")
                     val uitype: String? ,
                     @SerializedName("value")
                     val value: String? ,
                     @SerializedName("key")
                     val key :String? ,
                     @SerializedName("hint")
                     val hint :String?  )