package com.krithi.frauddetector.network.repository

import android.util.Log
import com.krithi.frauddetector.BuildConfig
import com.krithi.frauddetector.network.RetrofitClient
import com.krithi.frauddetector.network.SafeBrowsingRequest
import com.krithi.frauddetector.network.ClientInfo
import com.krithi.frauddetector.network.ThreatInfo
import com.krithi.frauddetector.network.ThreatEntry

class UrlScannerRepository {

    private val api = RetrofitClient.api

    suspend fun checkUrl(url: String): String {

        return try {

            val request = SafeBrowsingRequest(

                client = ClientInfo(
                    clientId = "SafeLens",
                    clientVersion = "1.0"
                ),

                threatInfo = ThreatInfo(
                    threatTypes = listOf(
                        "MALWARE",
                        "SOCIAL_ENGINEERING"
                    ),

                    platformTypes = listOf(
                        "ANY_PLATFORM"
                    ),

                    threatEntryTypes = listOf(
                        "URL"
                    ),

                    threatEntries = listOf(
                        ThreatEntry(url)
                    )
                )
            )

            val response = api.checkUrl(
                apiKey = BuildConfig.SAFE_BROWSING_API_KEY,
                request = request
            )

            if (response.matches.isNotEmpty()) {

                val match = response.matches.first()

                when (match.threatType) {

                    "MALWARE" ->
                        "🚨 Malware Threat Detected"

                    "SOCIAL_ENGINEERING" ->
                        "🚨 Phishing / Social Engineering Detected"

                    else ->
                        "🚨 Dangerous URL Detected"
                }

            } else {

                "✅ URL appears safe"
            }

        } catch (e: Exception) {

            Log.e(
                "SafeBrowsing",
                "URL scan failed",
                e
            )

            "⚠️ Unable to check URL"
        }
    }
}