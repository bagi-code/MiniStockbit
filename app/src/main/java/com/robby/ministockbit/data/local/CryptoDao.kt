package com.robby.ministockbit.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CryptoDao {

    @Query("SELECT * FROM crypto")
    fun findAll(): LiveData<List<CryptoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<CryptoEntity>)
}