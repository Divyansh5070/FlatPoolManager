package com.divyansh.flatpoolmanager.data

import androidx.compose.ui.graphics.Color

data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "member", // "admin" or "member"
    val avatarColor: Long = 0xFF2196F3 // Default blue color
) {
    companion object {
        // Predefined users for the flat
        val PREDEFINED_USERS = listOf(
            User(
                userId = "divyansh",
                name = "Divyansh",
                email = "divyansh@flatpool.com",
                role = "admin",
                avatarColor = 0xFF2196F3 // Blue
            ),
            User(
                userId = "tanwar",
                name = "Tanwar",
                email = "tanwar@flatpool.com",
                role = "member",
                avatarColor = 0xFF4CAF50 // Green
            ),
            User(
                userId = "jangir",
                name = "Jangir",
                email = "jangir@flatpool.com",
                role = "member",
                avatarColor = 0xFFFF9800 // Orange
            ),
            User(
                userId = "sahil",
                name = "Sahil",
                email = "sahil@flatpool.com",
                role = "member",
                avatarColor = 0xFF9C27B0 // Purple
            ),
            User(
                userId = "jashan",
                name = "Jashan",
                email = "jashan@flatpool.com",
                role = "member",
                avatarColor = 0xFF009688 // Teal
            ),
            User(
                userId = "ritu",
                name = "Ritu",
                email = "ritu@flatpool.com",
                role = "member",
                avatarColor = 0xFFE91E63 // Pink
            )
        )

        // Predefined passwords (for demo purposes only)
        val USER_PASSWORDS = mapOf(
            "divyansh@flatpool.com" to "divyansh123",
            "tanwar@flatpool.com" to "tanwar123",
            "jangir@flatpool.com" to "jangir123",
            "sahil@flatpool.com" to "sahil123",
            "jashan@flatpool.com" to "jashan123",
            "ritu@flatpool.com" to "ritu123"
        )

        fun getUserByEmail(email: String): User? {
            return PREDEFINED_USERS.find { it.email == email }
        }

        fun isAdmin(userId: String): Boolean {
            return PREDEFINED_USERS.find { it.userId == userId }?.role == "admin"
        }
    }
}
