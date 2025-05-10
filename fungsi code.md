SplashActivity.kt
```kotlin
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
```
bagian diatas digunakan untuk mendefinisikan package dan meng import kelas yang dibutuhkan.

```kotlin
class SplashActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var statusTextView: TextView
```
bagian diatas digunakan untuk mendefinisikan kelas SplashActivity sebagai pembuka yang muncul saat aplikasi pertama dibuka. variabel UI ProgressBar digunakan untuk menampilkan kemajuan loading dan statusTextView untuk menampilkan pesan yang terkait ke kemajuan loading. menggunakan lateinit karena akan diinisalisasi sebelum digunakan

```kotlin
private val loadingStages = listOf(
        LoadingStage(0, 10, "Loading information...", 1000L),
        LoadingStage(10, 35, "Verifying information..", 1500L),
        LoadingStage(35, 70, "Verifying information...", 1500L),
        LoadingStage(70, 95, "verification completed", 1000L),
        LoadingStage(95, 100, "Welcome!", 500L)
    )
    private val pauseDuration = 1000L
```
bagian diatas digunakan untuk mendefinisikan tahapan loading dan juga pause duration untuk melakukan pause setiap 1 loading stage

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        progressBar = findViewById(R.id.loadingProgressBar)
        statusTextView = findViewById(R.id.loadingStatusTextView)
        startStaggeredLoading()
    }
```
digunakan untuk mengatur layout SplashActivity menggunakan activity_splash.xml, menyembunyikan action bar agar tampilan fullscreen, menginisialisasi ProgressBar dan statusTextView dengan elemen dari layout, memulai proses loading dengan pause.

```kotlin
private fun startStaggeredLoading() {
        loadNextStage(0)
    }
```
memulai proses loading

```kotlin
private fun loadNextStage(stageIndex: Int) {
        if (stageIndex >= loadingStages.size) {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
            return
        }
```
berfungsi untuk memindahkan ke MenuActivity saat SplashActivity selesai

```kotlin
val stage = loadingStages[stageIndex]
        if (stage.text.isNotEmpty()) {
            statusTextView.text = stage.text
        }
```
untuk mengambil objek tahapan loading dan memperbarui tahapan loading tergantung persentase

```kotlin
val animator = ValueAnimator.ofInt(stage.startProgress, stage.endProgress)
        animator.duration = stage.duration
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            val currentProgress = animation.animatedValue as Int
            progressBar.progress = currentProgress
        }
        animator.start()
```
untuk membuat dan konfigurasi animasi progress bar

```kotlin
val delayToNextStage = stage.duration +
                if (stageIndex < loadingStages.size - 1) pauseDuration else 0L
        Handler(Looper.getMainLooper()).postDelayed({
            loadNextStage(stageIndex + 1)
        }, delayToNextStage)
    }
```
bagian akhir untuk menjadwalkan pemindahan ke tahap berikutnya

```kotlin
data class LoadingStage(
        val startProgress: Int,
        val endProgress: Int,
        val text: String,
        val duration: Long
    )
}
```
pendefinisian loading stage sebagai data class

MenuActivity.kt
```kotlin
package id.project.securewire

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
```
definisi package dan import

```kotlin
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar?.hide()
```
awal dari kelas MenuActivity dan untuk menyembunyikan action bar

```kotlin
findViewById<AppCompatButton>(R.id.loginButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
```
digunakan untuk mencari tombol login dengan id dan menambahkan onclick listener untuk membuka LoginActivity saat di klik

```kotlin
findViewById<AppCompatButton>(R.id.loginButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
```
sama dengan fungsi tombol login, hanya saja digunakan untuk memindahakan user ke RegisterActivity saat di klik



















