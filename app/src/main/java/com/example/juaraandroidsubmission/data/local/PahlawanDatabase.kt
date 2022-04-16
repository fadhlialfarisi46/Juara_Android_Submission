package com.example.juaraandroidsubmission.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pahlawan::class], version = 1, exportSchema = false)
abstract class PahlawanDatabase: RoomDatabase() {

    abstract fun pahlawanDao(): PahlawanDao

    companion object{
        @Volatile
        private var INSTANCE: PahlawanDatabase? = null

        fun getDatabase(context: Context): PahlawanDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PahlawanDatabase::class.java,
                    "pahlawan_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}