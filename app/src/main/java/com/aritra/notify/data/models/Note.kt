package com.aritra.notify.data.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.aritra.notify.utils.DateTypeConverter
import kotlinx.parcelize.Parcelize
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.Date

@Parcelize
@Entity(tableName = "note")
@TypeConverters(DateTypeConverter::class)
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String,
    var note: String,
    var dateTime: Date?,
    @TypeConverters(BitmapConverters::class)
    var imagePath: List<Bitmap>?
) : Parcelable
class BitmapConverters {
    @TypeConverter
    fun fromBitmapList(bitmapList: List<Bitmap>?): ByteArray? {
        if (bitmapList == null) {
            return null
        }

        val outputStream = ByteArrayOutputStream()
        bitmapList.forEach { bitmap ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        }
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmapList(byteArray: ByteArray?): List<Bitmap>? {
        if (byteArray == null) {
            return null
        }

        val bitmapList = mutableListOf<Bitmap>()
        val inputStream = ByteArrayInputStream(byteArray)
        while (inputStream.available() > 0) {
            val bitmap = BitmapFactory.decodeStream(inputStream)
            bitmapList.add(bitmap)
        }
        return bitmapList
    }
}