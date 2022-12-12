package com.padcmyanmar.thiha.thelibraryapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookListVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.BookVO
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.thiha.thelibraryapp.persistence.daos.BookListDao
import com.padcmyanmar.thiha.thelibraryapp.persistence.daos.RecentBookDao
import com.padcmyanmar.thiha.thelibraryapp.persistence.daos.ShelfDao

@Database(entities = [BookVO::class,BookListVO::class,ShelfVO::class], version = 7, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {
    companion object{
        const val DB_NAME = "THE_LIBRARY_DATABASE"
        var dbInstant : LibraryDatabase? = null

        fun getDBInstant(context: Context) : LibraryDatabase?{
            when(dbInstant){
                null -> {
                    dbInstant = Room.databaseBuilder(context, LibraryDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstant
        }
    }

    abstract fun recentBookDao() : RecentBookDao
    abstract fun bookListDao() : BookListDao
    abstract fun shelfDao() : ShelfDao

}