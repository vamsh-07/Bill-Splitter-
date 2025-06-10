package com.example.billsplitter.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.billsplitter.data.AppDatabase
import com.example.billsplitter.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)

        binding.btnAddExpense.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }

        lifecycleScope.launch {
            val expenses = db.expenseDao().getAllExpenses()
            binding.tvExpenses.text = expenses.joinToString("\n") {
                "${it.payer} paid ₹${it.amount} for ${it.participants}"
            }
        }
    }
}
