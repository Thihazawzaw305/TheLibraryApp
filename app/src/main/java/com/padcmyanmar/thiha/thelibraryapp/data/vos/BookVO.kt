package com.padcmyanmar.thiha.thelibraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//
//@Entity(tableName = "books")
//data class BookVO(
//    @ColumnInfo(name = "age_group")
//    @SerializedName("age_group")
//    val ageGroup : String?,
//
//    @ColumnInfo(name = "author")
//    @SerializedName("author")
//    val author : String?,
//
//
//    @ColumnInfo(name ="book_image")
//    @SerializedName("book_image")
//    val bookImage : String?,
//
//
//    @ColumnInfo(name = "book_image_width")
//    @SerializedName("book_image_width")
//    val bookImageWidth : Int,
//
//    @ColumnInfo(name = "book_image_height")
//    @SerializedName("book_image_height")
//    val bookImageHeight : Int?,
//
//
//    @ColumnInfo(name = "contributor")
//    @SerializedName("contributor")
//    val contributor : String?,
//
//
//    @ColumnInfo(name = "created_date")
//    @SerializedName("created_date")
//    val createdDate : String?,
//
//
//    @ColumnInfo(name = "description")
//    @SerializedName("description")
//    val description : String?,
//
//
//    @ColumnInfo(name = "price")
//    @SerializedName("price")
//    val price : String?,
//
//
//    @ColumnInfo(name = "book_uri")
//    @SerializedName("book_uri")
//    val bookUri : String?,
//
//    @ColumnInfo(name ="publisher")
//    @SerializedName("publisher")
//    val publisher : String?,
//
//    @ColumnInfo(name = "rank")
//    @SerializedName("rank")
//    val rank : Int?,
//
//    @ColumnInfo(name = "rank_last_week")
//    @SerializedName("rank_last_week")
//    val rankLastWeek : Int?,
//
//    @PrimaryKey
//    @ColumnInfo(name = "title")
//    @SerializedName("title")
//    val title : String,
//
//
//    @ColumnInfo(name = "updated_date")
//    @SerializedName("updated_date")
//    val updatedDate : String?,
//
//
//    @ColumnInfo(name = "weeks_on_list")
//    @SerializedName("weeks_on_list")
//    val weekOnList : Int?,
//
//    @ColumnInfo(name = "date_millis")
//    @SerializedName("date_millis")
//    var dateMillis:Long = 0,
//
//    @ColumnInfo(name = "book_list_name")
//    @SerializedName("book_list_name")
//    var bookListName:String = "",
//)



@Entity(tableName = "books")
data class BookVO(
    @ColumnInfo(name = "age_group")
    @SerializedName(value = "age_group")
    val ageGroup: String?,

    @ColumnInfo(name = "author")
    @SerializedName(value = "author")
    val author: String?,

    @ColumnInfo(name = "book_image")
    @SerializedName(value = "book_image")
    val bookImage:String?,

    @ColumnInfo(name = "contributor")
    @SerializedName(value = "contributor")
    val contributor:String?,

    @ColumnInfo(name = "created_date")
    @SerializedName(value = "created_date")
    val createdDate:String?,

    @ColumnInfo(name = "description")
    @SerializedName(value = "description")
    val description:String?,

    @ColumnInfo(name = "price")
    @SerializedName(value = "price")
    val price:String?,

    @ColumnInfo(name = "publisher")
    @SerializedName(value = "publisher")
    val publisher: String?,

    @ColumnInfo(name = "rank")
    @SerializedName(value = "rank")
    val rank: Int?,

    @ColumnInfo(name = "rank_last_week")
    @SerializedName(value = "rank_last_week")
    val rankLastWeek: Int?,

    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName(value = "title")
    var title: String,

    @ColumnInfo(name = "updated_date")
    @SerializedName(value = "updated_date")
    val updatedDate: String?,

    @ColumnInfo(name = "book_list_name")
    @SerializedName("book_list_name")
    var bookListName:String = "",

    @ColumnInfo(name = "date_millis")
    @SerializedName("date_millis")
    var dateMillis:Long = 0,

    ) {
}