package com.cc221010.quickmaths.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "scores")
data class score (
    val name:String,
    val points:Int,
    val date:LocalDateTime?,
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
)