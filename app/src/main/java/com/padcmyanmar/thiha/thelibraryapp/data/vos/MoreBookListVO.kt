package com.padcmyanmar.thiha.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class MoreBookListVO(

    @SerializedName("list_name_encoded")
    val listNameEncoded: String?,

    @SerializedName("list_name")
    val listName: String?,

    @SerializedName("book_details")
    val bookDetails: List<BookVO>?,
) {
}