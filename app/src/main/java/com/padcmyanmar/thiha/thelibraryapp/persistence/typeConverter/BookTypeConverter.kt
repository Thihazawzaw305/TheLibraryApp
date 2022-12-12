package com.padcmyanmar.thiha.thelibraryapp.persistence.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO

class BookTypeConverter {
    @TypeConverter
    fun toString(actorList: List<BookVO>?): String {
        return Gson().toJson(actorList)
    }

    @TypeConverter
    fun toBookList(jsonStr: String) : List<BookVO>?{
        val type = object : TypeToken<List<BookVO>?>(){}.type
        return Gson().fromJson(jsonStr, type)
    }
}