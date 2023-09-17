package com.aritra.notify.utils

import android.graphics.Bitmap
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class BitmapConverter {

    @TypeConverter
    fun fromListToString(list: List<Bitmap>): String {
        val type = object: TypeToken<List<*>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun toBitmap(dataString: String?): List<Bitmap> {
        if(dataString.isNullOrEmpty()) {
            return mutableListOf()
        }
        val type: Type = object : TypeToken<List<Bitmap>>() {}.type
        return Gson().fromJson(dataString, type)
    }

}