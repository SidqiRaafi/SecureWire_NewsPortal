package id.project.securewire

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var statusTextView: TextView
    private val totalDuration = 5500L
    private val loadingStages = listOf(
        LoadingStage(0, 10, "Loading information...", 1000L),
        LoadingStage(10, 35, "Verifying information..", 1500L),
        LoadingStage(35, 70, "Verifying information...", 1500L),
        LoadingStage(70, 95, "verification completed", 1000L),
        LoadingStage(95, 100, "Welcome!", 500L)
    )
    private val pauseDuration = 1000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        progressBar = findViewById(R.id.loadingProgressBar)
        statusTextView = findViewById(R.id.loadingStatusTextView)
        startStaggeredLoading()
    }
    private fun startStaggeredLoading() {
        loadNextStage(0)
    }
    private fun loadNextStage(stageIndex: Int) {
        if (stageIndex >= loadingStages.size) {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
            return
        }
        val stage = loadingStages[stageIndex]
        if (stage.text.isNotEmpty()) {
            statusTextView.text = stage.text
        }
        val animator = ValueAnimator.ofInt(stage.startProgress, stage.endProgress)
        animator.duration = stage.duration
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            val currentProgress = animation.animatedValue as Int
            progressBar.progress = currentProgress
        }
        animator.start()
        val delayToNextStage = stage.duration +
                if (stageIndex < loadingStages.size - 1) pauseDuration else 0L
        Handler(Looper.getMainLooper()).postDelayed({
            loadNextStage(stageIndex + 1)
        }, delayToNextStage)
    }
    data class LoadingStage(
        val startProgress: Int,
        val endProgress: Int,
        val text: String,
        val duration: Long
    )
}