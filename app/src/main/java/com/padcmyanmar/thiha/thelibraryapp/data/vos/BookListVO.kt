package com.padcmyanmar.thiha.thelibraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.thiha.thelibraryapp.persistence.typeConverter.BookTypeConverter



@Entity(tableName = "bookList")
@TypeConverters(
    BookTypeConverter::class
)
data class BookListVO(
    @PrimaryKey
    @ColumnInfo(name = "list_id")
    @SerializedName("list_id")
    val listId: Int,

    @ColumnInfo(name = "list_name_encoded")
    @SerializedName("list_name_encoded")
    val listNameEncoded: String?,

    @ColumnInfo(name = "list_name")
    @SerializedName("list_name")
    val listName: String?,

    @ColumnInfo(name = "books")
    @SerializedName("books")
    val books: List<BookVO>?,
) {
}




















//
//@Entity(tableName = "bookList")
//@TypeConverters(
//    BookTypeConverter::class
//)
//data class BookListVO(
//
//    @PrimaryKey
//    @ColumnInfo(name = "list_id")
//    @SerializedName("list_id")
//    val listId: Int,
//
//    @ColumnInfo(name = "list_name")
//    @SerializedName("list_name")
//    val listName: String?,
//
//    @ColumnInfo(name = "list_name_encoded")
//    @SerializedName("list_name_encoded")
//    val listNameEncoded: String?,
//
//    @ColumnInfo(name = "display_name")
//    @SerializedName("display_name")
//    val displayName: String?,
//
//
//    @ColumnInfo(name = "books")
//    @SerializedName("books")
//    val books: List<BookVO>?
//
//
//)