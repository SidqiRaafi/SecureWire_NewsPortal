package id.project.securewire

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val backTextView = findViewById<TextView>(R.id.backTextView)
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            startActivity(Intent(this, NewsPortalActivity::class.java))
            finish()
        }

        backTextView.setOnClickListener {
            finish()
        }

        forgotPasswordTextView.setOnClickListener {
            Toast.makeText(this, "Forgot Password feature not implemented", Toast.LENGTH_SHORT).show()
        }
    }
}