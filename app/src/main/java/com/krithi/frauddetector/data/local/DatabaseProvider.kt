package com.krithi.frauddetector.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: SafeLensDatabase? = null

    fun getDatabase(context: Context): SafeLensDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                SafeLensDatabase::class.java,
                "safelens_database"
            ).build()

            INSTANCE = instance

            instance
        }
    }
}