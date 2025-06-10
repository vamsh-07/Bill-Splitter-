package com.example.billsplitter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val payer: String,
    val amount: Double,
    val participants: String // comma-separated names
)
