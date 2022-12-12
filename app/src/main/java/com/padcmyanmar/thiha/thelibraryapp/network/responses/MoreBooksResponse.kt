package com.padcmyanmar.thiha.thelibraryapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.thelibraryapp.data.vos.MoreBookListVO


data class MoreBooksResponse(
    @SerializedName(value = "status")
    val status: String?,

    @SerializedName(value = "results")
    val results: List<MoreBookListVO>
)
