package com.krithi.frauddetector.network

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SafeBrowsingApi {

    @Headers(
        "X-Android-Package: com.krithi.frauddetector",
        "X-Android-Cert: D9:EC:67:83:5F:81:61:6F:0C:17:9B:B2:8D:C7:E0:E6:45:EC:BA:C4"
    )
    @POST("v4/threatMatches:find")
    suspend fun checkUrl(
        @retrofit2.http.Header("x-goog-api-key") apiKey: String,
        @Body request: SafeBrowsingRequest
    ): SafeBrowsingResponse
}