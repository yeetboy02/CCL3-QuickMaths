package com.cc221010.quickmaths.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cc221010.quickmaths.data.models.score
import kotlinx.coroutines.flow.Flow

@Dao
interface scoreDAO {
    @Insert
    suspend fun insertScore(score:score);

    @Update
    suspend fun updateScore(score:score);

    @Delete
    suspend fun deleteScore(score:score);

    @Query("SELECT * FROM scores ORDER BY points DESC")
    fun getScores():Flow<List<score>>

    @Query("SELECT * FROM scores WHERE date = ( SELECT MAX(date) FROM scores )")
    fun getLastScore():score;

    @Query("SELECT * FROM scores WHERE id = :id")
    fun getScore(id:Int):Flow<score>;

}