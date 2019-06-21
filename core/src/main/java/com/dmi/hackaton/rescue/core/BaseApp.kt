package com.dmi.hackaton.rescue.core

import android.app.Application
import android.util.Log
import com.dmi.hackaton.rescue.core.api.ApiProvider
import com.dmi.hackaton.rescue.core.api.RemoteService
import com.dmi.hackaton.rescue.core.data.SubscriptionRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import io.reactivex.schedulers.Schedulers

abstract class BaseApp : Application() {
    
    protected open fun getUserId(): Long? = null
    
    override fun onCreate() {
        super.onCreate()
        registerNotification()
    }
    
    fun registerNotification(onComplete: (() -> Unit)? = null) {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.d("@@@", "getInstanceId failed", task?.exception)
                    } else {
                        val token = task.result?.token
                        if (token != null) {
                            sendRegistrationTokenToServer(token, onComplete)
                        }
                    }
                })
    }
    
    fun sendRegistrationTokenToServer(token: String, onComplete: (() -> Unit)? = null) {
        val id = getUserId()
        if (shouldSendWhenNoUserId()) {
            val remote = ApiProvider.getRetrofit().create(RemoteService::class.java)
            val request = SubscriptionRequest(token!!, id)
            remote.subscribe(request).subscribeOn(Schedulers.io()).subscribe({
                Log.d("@@@", "sub success, token: $token")
                onComplete?.invoke()
            }, {
                Log.d("@@@", "sub failed " + it.message)
            })
        }
    }
    
    protected open fun shouldSendWhenNoUserId(): Boolean = true
    
}
