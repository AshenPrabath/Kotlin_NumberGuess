package com.example.wordguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var tvPrompt: TextView
    private lateinit var etGuess: EditText
    private lateinit var btnSubmit: Button
    private lateinit var tvResult: TextView

    private var randomNumber: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPrompt = findViewById(R.id.tvPrompt)
        etGuess = findViewById(R.id.etGuess)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvResult = findViewById(R.id.tvResult)

        startNewGame()

        btnSubmit.setOnClickListener {
            val userGuess = etGuess.text.toString()

            // Check if input is valid
            if (userGuess.isNotEmpty()) {
                val guessedNumber = userGuess.toInt()
                checkGuess(guessedNumber)
            } else {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun startNewGame() {
        randomNumber = Random.nextInt(1, 21) // Random number between 1 and 20
        tvResult.text = "" // Clear result text
        etGuess.text.clear() // Clear input field
    }
    private fun checkGuess(guessedNumber: Int) {
        when {
            guessedNumber < randomNumber -> {
                tvResult.text = "Too low! Try again."
            }
            guessedNumber > randomNumber -> {
                tvResult.text = "Too high! Try again."
            }
            else -> {
                tvResult.text = "Correct! The number was $randomNumber"
                btnSubmit.isEnabled = false // Disable submit button when the game is won
                Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show()

                // Optionally, you could reset the game after a correct guess
                // startNewGame()
            }
        }
    }
}