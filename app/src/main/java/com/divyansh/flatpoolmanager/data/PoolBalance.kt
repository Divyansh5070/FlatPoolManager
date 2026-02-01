package com.divyansh.flatpoolmanager.data

data class PoolBalance(
    val id: Int = 1,
    val balance: Double = 0.0
) {
    // No-argument constructor for Firebase
    constructor() : this(1, 0.0)
}