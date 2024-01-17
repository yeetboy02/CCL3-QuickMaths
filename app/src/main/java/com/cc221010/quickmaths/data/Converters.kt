package com.cc221010.quickmaths.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) };
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString();
    }
}

@RequiresApi(Build.VERSION_CODES.O)
val dateFormat:DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");