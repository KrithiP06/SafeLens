package com.krithi.frauddetector.network

data class SafeBrowsingRequest(
    val client: ClientInfo,
    val threatInfo: ThreatInfo
)

data class ClientInfo(
    val clientId: String,
    val clientVersion: String
)

data class ThreatInfo(
    val threatTypes: List<String>,
    val platformTypes: List<String>,
    val threatEntryTypes: List<String>,
    val threatEntries: List<ThreatEntry>
)

data class ThreatEntry(
    val url: String
)

data class SafeBrowsingResponse(
    val matches: List<ThreatMatch> = emptyList()
)

data class ThreatMatch(
    val threatType: String? = null,
    val platformType: String? = null,
    val threatEntryType: String? = null,
    val threat: ThreatEntry? = null
)