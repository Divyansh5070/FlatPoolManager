package com.divyansh.flatpoolmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.divyansh.flatpoolmanager.data.Payment
import com.divyansh.flatpoolmanager.data.User
import com.divyansh.flatpoolmanager.repository.FirebaseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PoolViewModel(
    private val firebaseRepository: FirebaseRepository = FirebaseRepository(),
    private val currentUser: User
) : ViewModel() {

    val balance: StateFlow<Double> = firebaseRepository.getBalance()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    val payments: StateFlow<List<Payment>> = firebaseRepository.getAllPayments()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            firebaseRepository.initializeBalance()
        }
    }

    // Add a new payment (deducts from balance)
    fun addPayment(
        flatmateName: String,
        amount: Double,
        itemName: String
    ) {
        viewModelScope.launch {
            val payment = Payment(
                flatmateName = flatmateName,
                amount = amount,
                itemName = itemName,
                date = System.currentTimeMillis(),
                isContribution = false,
                userId = currentUser.userId
            )
            firebaseRepository.addPayment(payment)

            val currentBalance = balance.value
            val newBalance = currentBalance - amount
            firebaseRepository.updateBalance(newBalance)
        }
    }

    // Add contribution (someone gave money) - only for admin
    fun addContribution(
        flatmateName: String,
        amount: Double
    ) {
        // Check if current user is admin
        if (currentUser.role != "admin") {
            return // Only admin can add money
        }

        viewModelScope.launch {
            val payment = Payment(
                flatmateName = flatmateName,
                amount = amount,
                itemName = "Contribution",
                date = System.currentTimeMillis(),
                isContribution = true,
                userId = currentUser.userId
            )
            firebaseRepository.addPayment(payment)

            val currentBalance = balance.value
            val newBalance = currentBalance + amount
            firebaseRepository.updateBalance(newBalance)
        }
    }

    fun deletePayment(payment: Payment) {
        viewModelScope.launch {
            // First update the balance
            val currentBalance = balance.value
            // If it was a contribution (money added), remove it from balance
            // If it was an expense (money spent), add it back to balance
            val newBalance = if (payment.isContribution) {
                currentBalance - payment.amount  // Remove the money that was added
            } else {
                currentBalance + payment.amount  // Add back the money that was spent
            }
            
            // Then delete the payment record
            firebaseRepository.deletePayment(payment)
            firebaseRepository.updateBalance(newBalance)
        }
    }

    fun isAdmin(): Boolean {
        return currentUser.role == "admin"
    }

    // Manually set balance (Admin only)
    fun setBalance(newBalance: Double) {
        if (currentUser.role != "admin") {
            return // Only admin can set balance
        }

        viewModelScope.launch {
            firebaseRepository.updateBalance(newBalance)
        }
    }
}
