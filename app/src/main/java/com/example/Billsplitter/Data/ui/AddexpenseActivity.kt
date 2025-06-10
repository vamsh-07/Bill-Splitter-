package com.example.billsplitter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.billsplitter.data.AppDatabase
import com.example.billsplitter.data.Expense
import com.example.billsplitter.databinding.ActivityAddExpenseBinding
import kotlinx.coroutines.launch

class AddExpenseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabase.getDatabase(this)

        binding.btnSave.setOnClickListener {
            val payer = binding.etPayer.text.toString()
            val amount = binding.etAmount.text.toString().toDoubleOrNull()
            val participants = binding.etParticipants.text.toString()

            if (payer.isEmpty() || amount == null || participants.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                db.expenseDao().insertExpense(Expense(payer = payer, amount = amount, participants = participants))
                finish()
            }
        }
    }
}
