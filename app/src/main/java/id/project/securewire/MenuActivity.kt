package id.project.securewire

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar?.hide()
        findViewById<AppCompatButton>(R.id.loginButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        findViewById<AppCompatButton>(R.id.registerButton).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}