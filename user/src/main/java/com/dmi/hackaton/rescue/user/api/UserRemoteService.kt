package com.dmi.hackaton.rescue.user.api

import com.dmi.hackaton.rescue.user.data.EmergencyRequest
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRemoteService {
    
    @POST("/api/v1/emergency")
    fun askForHelp(@Body request: EmergencyRequest): Completable
    
}
