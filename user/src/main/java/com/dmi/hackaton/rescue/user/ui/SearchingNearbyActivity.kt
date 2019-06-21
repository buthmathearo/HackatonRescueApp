package com.dmi.hackaton.rescue.user.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.dmi.hackaton.rescue.core.api.ApiProvider
import com.dmi.hackaton.rescue.user.R
import com.dmi.hackaton.rescue.user.api.UserRemoteService
import com.dmi.hackaton.rescue.user.data.EmergencyRequest
import com.dmi.hackaton.rescue.user.intent.SearchingNearbyIntent
import com.dmi.hackaton.rescue.user.notification.MessagingService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import io.nlopez.smartlocation.SmartLocation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_searching_nearby.cancelRequest

class SearchingNearbyActivity : BaseActivity() {
    
    private var apiDisposable: Disposable? = null
    private var messageDisposable: Disposable? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.view_searching_nearby)
        
        cancelRequest.setOnClickListener {
            finish()
        }
    
        messageDisposable = MessagingService.bus.subscribe { message ->
            setResult(Activity.RESULT_OK)
            finish()
        }
        
        askForHelp()
    }
    
    private fun askForHelp() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    val token = task.result?.token
                    if (token != null) {
                        val myIntent = SearchingNearbyIntent(intent)
                        onGetTokenSuccess(myIntent.getVictimCount(), token)
                    }
                })
        
    }
    
    private fun onGetTokenSuccess(count: Int, token: String) {
        SmartLocation.with(this).location().start { location ->
            if (location != null) {
                val request = EmergencyRequest(count, token, location.latitude, location.longitude)
                val remote = ApiProvider.getRetrofit().create(UserRemoteService::class.java)
                apiDisposable = remote.askForHelp(request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Log.d("@@@", "Send location success $location")
                        }, {
                            Log.d("@@@", "Send location failed $location")
                        })
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        
        if (apiDisposable != null && !apiDisposable!!.isDisposed) {
            apiDisposable!!.dispose()
        }
    
        if (messageDisposable != null && !messageDisposable!!.isDisposed) {
            messageDisposable!!.dispose()
        }
        
        SmartLocation.with(this).location().stop()
    }
    
    override fun onBackPressed() {
        // Not allow to press back.
    }
}
