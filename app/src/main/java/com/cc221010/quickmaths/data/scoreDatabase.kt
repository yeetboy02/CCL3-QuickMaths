package com.cc221010.quickmaths.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cc221010.quickmaths.data.models.score

@Database(entities = [score::class], version = 1)
abstract class scoreDatabase:RoomDatabase() {
    abstract val dao:scoreDAO;
}