package com.example.juaraandroidsubmission.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PahlawanDao {

    @Query("SELECT * FROM pahlawan")
    fun getPahlawans(): LiveData<List<Pahlawan>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pahlawans: List<Pahlawan>)
}