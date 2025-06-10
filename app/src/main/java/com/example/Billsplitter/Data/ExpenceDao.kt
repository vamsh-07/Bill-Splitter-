package com.example.billsplitter.data

import androidx.room.*

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Query("SELECT * FROM Expense")
    suspend fun getAllExpenses(): List<Expense>
}
