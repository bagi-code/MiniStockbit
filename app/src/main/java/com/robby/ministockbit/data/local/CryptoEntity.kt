package com.robby.ministockbit.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto")
data class CryptoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val fullname: String,
    val price: String,
    val changeHour: String,
    val changePCTHour: String,
)