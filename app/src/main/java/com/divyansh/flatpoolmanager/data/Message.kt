package com.divyansh.flatpoolmanager.data

data class Message(
    val messageId: String = "",
    val senderId: String = "",
    val senderName: String = "",
    val message: String = "",
    val timestamp: Long = 0L,
    val senderColor: Long = 0xFF2196F3
) {
    // No-argument constructor for Firebase
    constructor() : this("", "", "", "", 0L, 0xFF2196F3)
}
