package com.divyansh.flatpoolmanager.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.divyansh.flatpoolmanager.data.Message
import com.divyansh.flatpoolmanager.data.User
import com.divyansh.flatpoolmanager.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatViewModel(
    private val chatRepository: ChatRepository = ChatRepository(),
    private val currentUser: User
) : ViewModel() {

    val messages: StateFlow<List<Message>> = chatRepository.getMessages()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _isSending = MutableStateFlow(false)
    val isSending: StateFlow<Boolean> = _isSending

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun sendMessage(messageText: String) {
        if (messageText.isBlank()) return

        viewModelScope.launch {
            _isSending.value = true
            _error.value = null
            try {
                Log.d("ChatViewModel", "Attempting to send message: $messageText")
                val message = Message(
                    senderId = currentUser.userId,
                    senderName = currentUser.name,
                    message = messageText,
                    timestamp = System.currentTimeMillis(),
                    senderColor = currentUser.avatarColor
                )
                chatRepository.sendMessage(message)
                Log.d("ChatViewModel", "Message sent successfully")
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Error sending message", e)
                _error.value = "Failed to send: ${e.message}"
            } finally {
                _isSending.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
