package ca.lakeheadu.comp3025g_w2024_week9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import ca.lakeheadu.comp3025g_w2024_week9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Declare an instance of the binding class
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favouriteTVShows = DataManager.instance.deserializeJSON(this,  R.raw.tvshows)

        val firstAdapter = FirstAdapter(favouriteTVShows)
        // Use view binding to replace findViewById or synthetic properties
        binding.FirstRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = firstAdapter
        }
    }
}