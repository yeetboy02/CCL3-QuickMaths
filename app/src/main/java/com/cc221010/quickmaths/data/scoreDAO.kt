package com.cc221010.quickmaths.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cc221010.quickmaths.data.models.score
import kotlinx.coroutines.flow.Flow

interface scoreDAO {
    @Insert
    suspend fun insertScore(score:score);

    @Update
    suspend fun updateScore(score:score);

    @Delete
    suspend fun deleteScore(score:score);

    @Query("SELECT * FROM scores")
    fun getScores():Flow<List<score>>

    @Query("SELECT * FROM scores WHERE id = :id")
    fun getScore(id:Int):Flow<score>;

}