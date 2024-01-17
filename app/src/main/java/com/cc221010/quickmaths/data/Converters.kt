package com.cc221010.quickmaths.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) };
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        return date?.toString();
    }
}

@RequiresApi(Build.VERSION_CODES.O)
val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");