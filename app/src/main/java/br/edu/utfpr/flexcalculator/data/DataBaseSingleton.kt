package br.edu.utfpr.flexcalculator.data

import android.content.Context
import androidx.room.Room

object DatabaseSingleton {
    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "carros.db"
            ).build()
        }
        return database!!
    }
}