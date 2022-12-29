package com.assignment.util

import android.widget.EditText

object Constants {
    val baseURL: String
        get() {
            return "http://localhost/"
        }
    val customUiUrl: String
        get() {
            return "https://demo.ezetap.com/mobileapps/android_assignment.json"
        }
    const val CONNECT_TIMEOUT: Long = 60
    const val READ_TIMEOUT: Long = 60
    const val CALL_TIMEOUT: Long = 60

    const val EDIT_TEXT= "edittext"
    const val NAVIGATE_DATA= "navigate_data"

}