package com.padcmyanmar.thiha.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class GoogleBookVO(
    @SerializedName("volumeInfo")
    val volumeInfo: GoogleVolumeInfo?,
)

{
    fun transformVO() : BookVO{
        return BookVO(
            ageGroup = "",
            author = this.volumeInfo?.authors?.joinToString(separator = ",").toString(),
            bookImage = this.volumeInfo?.imageLinks?.thumbnail,
            contributor = "",
            createdDate = "",
            description = this.volumeInfo?.description,
            price = "",
            publisher = "",
            rank = 0,
            rankLastWeek = 0,
            title = this.volumeInfo?.title.toString(),
            updatedDate = "",
            bookListName = this.volumeInfo?.categories?.joinToString(separator = " ").toString(),
            dateMillis = 0
        )
    }
}