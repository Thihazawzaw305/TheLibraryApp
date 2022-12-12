package com.padcmyanmar.thiha.thelibraryapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.thelibraryapp.data.vos.GoogleBookVO

data class GoogleBookListResponse(
    @SerializedName(value = "items")
    val items: List<GoogleBookVO>?,

    )