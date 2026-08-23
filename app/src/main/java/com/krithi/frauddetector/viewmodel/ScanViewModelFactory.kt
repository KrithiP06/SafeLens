package com.krithi.frauddetector.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krithi.frauddetector.data.repository.ScanRepository
import com.krithi.frauddetector.network.repository.UrlScannerRepository

class ScanViewModelFactory(
    private val repository: ScanRepository,
    private val urlScannerRepository: UrlScannerRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ScanViewModel::class.java)) {
            return ScanViewModel(
                repository,
                urlScannerRepository
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}