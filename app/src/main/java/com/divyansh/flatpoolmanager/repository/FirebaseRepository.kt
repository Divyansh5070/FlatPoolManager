package com.divyansh.flatpoolmanager.repository

import com.divyansh.flatpoolmanager.data.Payment
import com.divyansh.flatpoolmanager.data.PoolBalance
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseRepository {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val paymentsRef = database.child("payments")
    private val poolRef = database.child("pool")

    // Get pool balance as Flow
    fun getBalance(): Flow<Double> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val balance = snapshot.child("balance").getValue(Double::class.java) ?: 0.0
                trySend(balance)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        
        poolRef.addValueEventListener(listener)
        
        awaitClose {
            poolRef.removeEventListener(listener)
        }
    }

    // Get all payments as Flow
    fun getAllPayments(): Flow<List<Payment>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val payments = mutableListOf<Payment>()
                snapshot.children.forEach { child ->
                    child.getValue(Payment::class.java)?.let { payment ->
                        payments.add(payment)
                    }
                }
                // Sort by date descending
                payments.sortByDescending { it.date }
                trySend(payments)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        
        paymentsRef.addValueEventListener(listener)
        
        awaitClose {
            paymentsRef.removeEventListener(listener)
        }
    }

    // Add payment
    suspend fun addPayment(payment: Payment) {
        val key = paymentsRef.push().key ?: return
        val paymentWithId = payment.copy(id = key.hashCode())
        paymentsRef.child(key).setValue(paymentWithId).await()
    }

    // Delete payment
    suspend fun deletePayment(payment: Payment) {
        // Find and delete the payment by iterating through all children
        val snapshot = paymentsRef.get().await()
        snapshot.children.forEach { child ->
            val p = child.getValue(Payment::class.java)
            if (p?.id == payment.id) {
                child.ref.removeValue().await()
                return
            }
        }
    }

    // Update balance
    suspend fun updateBalance(balance: Double) {
        poolRef.child("balance").setValue(balance).await()
    }

    // Initialize balance if not exists
    suspend fun initializeBalance() {
        val snapshot = poolRef.child("balance").get().await()
        if (!snapshot.exists()) {
            poolRef.child("balance").setValue(0.0).await()
        }
    }
}
