package com.divyansh.flatpoolmanager.repository

import com.divyansh.flatpoolmanager.data.Message
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ChatRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val messagesRef = database.child("messages")

    // Get all messages as Flow
    fun getMessages(): Flow<List<Message>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messages = mutableListOf<Message>()
                snapshot.children.forEach { child ->
                    child.getValue(Message::class.java)?.let { message ->
                        messages.add(message)
                    }
                }
                // Sort by timestamp ascending (oldest first)
                messages.sortBy { it.timestamp }
                trySend(messages)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        
        messagesRef.addValueEventListener(listener)
        
        awaitClose {
            messagesRef.removeEventListener(listener)
        }
    }

    // Send message
    suspend fun sendMessage(message: Message) {
        val key = messagesRef.push().key ?: return
        val messageWithId = message.copy(messageId = key)
        messagesRef.child(key).setValue(messageWithId).await()
    }

    // Delete all messages (for testing)
    suspend fun clearMessages() {
        messagesRef.removeValue().await()
    }
}
