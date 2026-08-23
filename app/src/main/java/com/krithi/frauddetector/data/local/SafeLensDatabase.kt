package com.krithi.frauddetector.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.krithi.frauddetector.data.model.ScanHistory

@Database(
    entities = [ScanHistory::class],
    version = 1,
    exportSchema = false
)
abstract class SafeLensDatabase : RoomDatabase() {

    abstract fun scanHistoryDao(): ScanHistoryDao
}