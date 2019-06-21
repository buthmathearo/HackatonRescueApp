package com.dmi.hackaton.rescue.user.data

data class EmergencyRequest(val numberOfVictim: Int,
                       val victimDeviceToken: String,
                       val latitude: Double,
                       val longitude: Double)
