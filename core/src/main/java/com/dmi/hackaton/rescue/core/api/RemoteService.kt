package com.dmi.hackaton.rescue.core.api

import com.dmi.hackaton.rescue.core.data.SubscriptionRequest
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteService {
    
    @POST("/api/v1/subscription")
    fun subscribe(@Body request: SubscriptionRequest): Completable
    
}
