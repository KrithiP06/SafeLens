package com.krithi.frauddetector.data.repository

import com.krithi.frauddetector.data.local.ScanHistoryDao
import com.krithi.frauddetector.data.model.ScanHistory
import kotlinx.coroutines.flow.Flow

class ScanRepository(
    private val dao: ScanHistoryDao
) {

    val allScans: Flow<List<ScanHistory>> =
        dao.getAllScans()

    suspend fun insertScan(scan: ScanHistory) {
        dao.insertScan(scan)
    }

    suspend fun clearHistory() {
        dao.clearHistory()
    }

    suspend fun deleteScan(scanId: Int) {
        dao.deleteScan(scanId)
    }
}