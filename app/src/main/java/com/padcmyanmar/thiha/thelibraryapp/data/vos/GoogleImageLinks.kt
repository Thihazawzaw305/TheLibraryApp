package com.padcmyanmar.thiha.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class GoogleImageLinks(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String?,

    @SerializedName("thumbnail")
    val thumbnail: String?,
){
    fun changeHttps() : String {
        return thumbnail?.substring(0,4) + "s" + thumbnail?.substring(4)
    }
}