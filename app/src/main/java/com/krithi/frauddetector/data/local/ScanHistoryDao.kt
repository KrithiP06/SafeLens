package com.krithi.frauddetector.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.krithi.frauddetector.data.model.ScanHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanHistoryDao {

    @Insert
    suspend fun insertScan(scan: ScanHistory)

    @Query("SELECT * FROM scan_history ORDER BY timestamp DESC")
    fun getAllScans(): Flow<List<ScanHistory>>

    @Query("DELETE FROM scan_history")
    suspend fun clearHistory()

    @Query("DELETE FROM scan_history WHERE id = :scanId")
    suspend fun deleteScan(scanId: Int)
}