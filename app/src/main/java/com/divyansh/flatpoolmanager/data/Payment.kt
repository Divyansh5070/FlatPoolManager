package com.divyansh.flatpoolmanager.data

data class Payment(
    val id: Int = 0,
    val flatmateName: String = "",
    val amount: Double = 0.0,
    val itemName: String = "",
    val date: Long = 0L,
    val isContribution: Boolean = false,
    val userId: String = ""
) {
    // No-argument constructor for Firebase
    constructor() : this(0, "", 0.0, "", 0L, false, "")
}