package com.dmi.hackaton.rescue.hospital.data

data class MyMessage(val alertId: Long,
                     val numberOfVictim: Int,
                     val message: String,
                     val latitude: Double,
                     val longitude: Double)
