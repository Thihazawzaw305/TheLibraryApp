package com.padcmyanmar.thiha.thelibraryapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.thiha.thelibraryapp.data.vos.ShelfVO


@Dao
interface ShelfDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelf(shelfVO: ShelfVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelfList(shelfList: List<ShelfVO>)

    @Query("SELECT * FROM shelves")
    fun getAllShelves(): LiveData<List<ShelfVO>>

    @Query("SELECT * FROM shelves WHERE name = :name")
    fun getShelfByName(name: String): LiveData<ShelfVO>

    @Query("SELECT * FROM shelves WHERE unique_id = :id")
    fun getShelfById(id: Long): LiveData<ShelfVO?>


    @Query("DELETE FROM shelves")
    fun deleteAllShelves()

    @Query("DELETE FROM shelves WHERE unique_id = :id")
    fun deleteShelfById(id: Long)

}
