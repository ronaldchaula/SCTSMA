package com.fyp.sctsma

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService: FirebaseMessagingService() {
    //this needs to be sent to the server for allowing new messages to be received
 override fun onNewToken(token: String) {
      super.onNewToken(token)
    }
    //this is called when a new message is received
override fun onMessageReceived(message: RemoteMessage) {
    super.onMessageReceived(message)
}
}