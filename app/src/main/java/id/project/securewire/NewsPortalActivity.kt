package id.project.securewire

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NewsPortalActivity : AppCompatActivity() {
    private val recommendationItems = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        "Praesent eget risus vitae massa semper aliquam",
        "Fusce pellentesque suscipit nibh vel molestie",
        "Nullam posuere lacus vel accumsan dignissim"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_portal)
        supportActionBar?.hide()
        val recommendationsList = findViewById<ListView>(R.id.recommendationsList)
        val adapter = object : ArrayAdapter<String>(
            this,
            R.layout.recommendation_item,
            R.id.recommendationText,
            recommendationItems
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(R.id.recommendationText)
                textView.text = recommendationItems[position]
                return view
            }
        }

        recommendationsList.adapter = adapter
    }
}