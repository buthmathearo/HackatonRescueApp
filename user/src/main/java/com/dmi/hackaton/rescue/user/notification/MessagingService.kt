package com.dmi.hackaton.rescue.user.notification

import com.dmi.hackaton.rescue.user.App
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.subjects.PublishSubject

class MessagingService : FirebaseMessagingService() {
    
    override fun onNewToken(newToken: String?) {
        super.onNewToken(newToken)
        if (newToken != null) {
            (baseContext.applicationContext as App).sendRegistrationTokenToServer(newToken)
        }
    }
    
    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        if (message != null) {
            bus.onNext(message)
        }
    }
    
    companion object {
        val bus = PublishSubject.create<RemoteMessage>()
    }
}
