package com.aritra.notify.data.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.aritra.notify.utils.BitmapConverter
import com.aritra.notify.utils.DateTypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.Date

@Parcelize
@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var note: String,
    @TypeConverters(DateTypeConverter::class)
    var dateTime: Date?,
    @TypeConverters(BitmapConverter::class)
    var imagePath: List<Bitmap>?
) : Parcelable
