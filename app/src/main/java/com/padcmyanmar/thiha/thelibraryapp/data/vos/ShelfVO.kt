package com.padcmyanmar.thiha.thelibraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.thelibraryapp.persistence.typeConverter.BookTypeConverter


@Entity(tableName = "shelves")
@TypeConverters(
    BookTypeConverter::class
)
data class ShelfVO(
    @PrimaryKey
    @ColumnInfo(name = "unique_id")
    @SerializedName("unique_id")
    val uniqueId: Long,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "books")
    @SerializedName("books")
    var books: List<BookVO> = listOf(),
)
