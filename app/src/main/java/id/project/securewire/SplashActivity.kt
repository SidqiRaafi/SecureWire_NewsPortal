package id.project.securewire

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val progressBar = findViewById<ProgressBar>(R.id.loadingCircle)
        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        progressAnimator.duration = 5000 // 5 seconds
        progressAnimator.start()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
        }, 5000)
    }
}