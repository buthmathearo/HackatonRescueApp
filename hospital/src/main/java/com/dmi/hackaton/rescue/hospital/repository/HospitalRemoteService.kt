package com.dmi.hackaton.rescue.hospital.repository

import com.dmi.hackaton.rescue.hospital.data.EmergencyAcceptedRequest
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface HospitalRemoteService {
    
    @POST("/api/v1/emergency/accept")
    fun acceptEmergency(@Body request: EmergencyAcceptedRequest): Completable
}
