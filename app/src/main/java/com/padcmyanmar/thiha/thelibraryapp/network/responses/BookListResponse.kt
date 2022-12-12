package com.padcmyanmar.thiha.thelibraryapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookResultVO


data class BookListResponse(
    @SerializedName(value = "status")
    val status: String?,

    @SerializedName(value = "results")
    val results: BookResultVO
)
