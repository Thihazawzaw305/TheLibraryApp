package com.padcmyanmar.thiha.thelibraryapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO

@Dao
interface BookListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBookList(bookList :List<BookListVO>)

    @Query("SELECT * FROM bookList")
    fun getAllBookList(): LiveData<List<BookListVO>>

    @Query("DELETE FROM bookList")
    fun deleteAllBookList()



}