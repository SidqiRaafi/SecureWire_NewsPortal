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

--------------------------------------------------------------------------------

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

--------------------------------------------------------------------------------

LoginActivity.kt

```kotlin
package id.project.securewire

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
```
definisi package dan import

```kotlin
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
```
awalan kelas LoginActivity dan mengatur layout dari activity_login.xml

```kotlin
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val backTextView = findViewById<TextView>(R.id.backTextView)
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
```
inisialisasi komponen UI dalam activity

```kotlin
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
```
logika untuk tombol login, memvalidasi bila semua field sudah diisi, menampilkan toast bila validasi gagal, jika berhasil ke NewsPortalActivity

```kotlin
        backTextView.setOnClickListener {
            finish()
        }
```
fungsi tombol back dengan menutup activity ini dan kembali ke MenuActivity

```kotlin
        forgotPasswordTextView.setOnClickListener {
            Toast.makeText(this, "Forgot Password feature not implemented", Toast.LENGTH_SHORT).show()
        }
    }
}
```
menampilkan toast bahwa fitur forgot password belum di implementasikan

--------------------------------------------------------------------------------

RegisterActivity.kt

```kotlin
package id.project.securewire

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
```
definisi package dan import

```kotlin
class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
```
awalan RegisterActivity, menyembunyikan action bar dan layout dari activity_register.xml

```kotlin
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val backTextView = findViewById<TextView>(R.id.backTextView)
        val loginTextView = findViewById<TextView>(R.id.loginTextView)
```
inisialisasi komponen UI dalam activity

```kotlin
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, NewsPortalActivity::class.java))
            finish()
        }
```
logika button registasi, untuk memvalidasi bila semua field diisi, menampilkan toast jika ada field kosong, ke NewsPortalActivity bila berhasil

```kotlin
        backTextView.setOnClickListener {
            finish()
        }
        loginTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
```
tombol back untuk kembali ke MenuActivity, dan login untuk ke LoginActivity

--------------------------------------------------------------------------------

NewsPortalActivity.kt

```kotlin
package id.project.securewire

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
```
definisi package dan import

```kotlin
class NewsPortalActivity : AppCompatActivity() {
    private val recommendationItems = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        "Praesent eget risus vitae massa semper aliquam",
        "Fusce pellentesque suscipit nibh vel molestie",
        "Nullam posuere lacus vel accumsan dignissim"
    )
```
deklarasi berita yang akan ditampilkan menggunakan dummy text

```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_portal)
        supportActionBar?.hide()
```
awalan dari NewsPortalActivity, menyembunyikan action bar, dan layout dari activity_news_portal.xml

```kotlin
        val recommendationsList = findViewById<ListView>(R.id.recommendationsList)
        val adapter = object : ArrayAdapter<String>(
            this,
            R.layout.recommendation_item,
            R.id.recommendationText,
            recommendationItems) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(R.id.recommendationText)
                textView.text = recommendationItems[position]
                return view
            }
        }
```
pembuatan adapter untuk listview di news portal, menggunakan layout custom dari recommendation_item.xml mengatur text dari data di recomendationItems

```kotlin
        recommendationsList.adapter = adapter
    }
}
```
mengatur adapter ke listview untuk menampilkan daftar berita ke user

















