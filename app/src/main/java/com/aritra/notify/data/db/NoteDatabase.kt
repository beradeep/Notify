package com.aritra.notify.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aritra.notify.data.dao.NoteDao
import com.aritra.notify.data.models.Note
import com.aritra.notify.utils.BitmapConverter
import com.aritra.notify.utils.DateTypeConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)
@TypeConverters(DateTypeConverter::class, BitmapConverter::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var INSTANCE: NoteDatabase? = null
        fun getInstance(context: Context): NoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "Note_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}