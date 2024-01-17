package com.cc221010.quickmaths.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cc221010.quickmaths.data.models.score

@Database(entities = [score::class], version = 4)
@TypeConverters(Converters::class)
abstract class scoreDatabase:RoomDatabase() {
    abstract val dao:scoreDAO;
}