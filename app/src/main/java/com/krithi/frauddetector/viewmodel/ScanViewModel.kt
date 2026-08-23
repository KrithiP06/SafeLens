package com.krithi.frauddetector.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krithi.frauddetector.data.model.ScanHistory
import com.krithi.frauddetector.data.repository.ScanRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.krithi.frauddetector.network.repository.UrlScannerRepository

class ScanViewModel(
    private val repository: ScanRepository,
    private val urlScannerRepository: UrlScannerRepository
) : ViewModel() {

    val scanHistory = repository.allScans
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun saveScan(
        type: String,
        input: String,
        result: String
    ) {

        viewModelScope.launch {

            repository.insertScan(
                ScanHistory(
                    type = type,
                    input = input,
                    result = result,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    fun checkUrl(
        url: String,
        onResult: (String) -> Unit
    ) {
        viewModelScope.launch {

            val result = urlScannerRepository.checkUrl(url)

            onResult(result)
        }
    }

    fun clearHistory() {

        viewModelScope.launch {
            repository.clearHistory()
        }
    }

    fun deleteScan(scanId: Int) {
        viewModelScope.launch {
            repository.deleteScan(scanId)
        }
    }
}