package com.krithi.frauddetector

import android.content.Context
import com.krithi.frauddetector.data.local.DatabaseProvider
import com.krithi.frauddetector.data.repository.ScanRepository
import com.krithi.frauddetector.network.repository.UrlScannerRepository

class AppContainer(context: Context) {

    private val database =
        DatabaseProvider.getDatabase(context)

    val repository =
        ScanRepository(database.scanHistoryDao())

    val urlScannerRepository =
        UrlScannerRepository()
}