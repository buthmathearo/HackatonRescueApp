package com.dmi.hackaton.rescue.core.data

class SubscriptionRequest(val deviceToken: String,
                          val userId: Long? = null,
                          val deviceType: String = ANDROID)
