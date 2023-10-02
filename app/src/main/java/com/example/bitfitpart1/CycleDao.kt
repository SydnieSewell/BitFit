package com.example.bitfitpart1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CycleDao {
    @Query("SELECT * FROM cycle_table")
    fun getAll(): Flow<List<CycleEntity>>

    @Insert
    fun insertAll(foods: List<CycleEntity>)

    @Query("DELETE FROM cycle_table")
    fun deleteAll()
}