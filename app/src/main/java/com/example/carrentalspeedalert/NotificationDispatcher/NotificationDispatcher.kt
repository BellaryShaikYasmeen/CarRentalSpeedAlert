package com.example.carrentalspeedalert.NotificationDispatcher

import com.example.carrentalspeedalert.AWSNotification
import com.example.carrentalspeedalert.FirebaseNotification
import com.example.carrentalspeedalert.NotificationStrategy
import com.example.carrentalspeedalert.domain.ChannelType

class NotificationDispatcher {
    fun getStrategy(channelType: ChannelType): NotificationStrategy {
        return when (channelType) {
            ChannelType.FIREBASE -> FirebaseNotification()
            ChannelType.AWS -> AWSNotification()
        }
    }
}
