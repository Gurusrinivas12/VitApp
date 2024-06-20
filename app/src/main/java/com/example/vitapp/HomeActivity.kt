package com.example.vitapp
import com.example.vitapp.databinding.ActivityHomeBinding
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitapp.network.MarsApi
import com.example.vitapp.network.MarsPhoto
import com.google.android.ads.mediationtestsuite.activities.HomeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private val TAG = HomeActivity::class.java.simpleName

    private lateinit var binding: ActivityHomeBinding

    //private lateinit var marsRecyclerView: RecyclerView

    private lateinit var marsAdapter: MarsAdapter
    private var photos: List<MarsPhoto> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        marsAdapter = MarsAdapter(photos)
        //marsRecyclerView.adapter = marsAdapter
        // setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // imageView = findViewById(R.id.imageView)
        // marsRecyclerView = findViewById(R.id.recyclerViewUrls)
        binding.recyclerViewUrls.layoutManager = LinearLayoutManager(this)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                binding.recyclerViewUrls.adapter = marsAdapter
                val listMarsPhotos = MarsApi.retrofitService.getPhotos()
                marsAdapter.listMarsPhotos = listMarsPhotos
                marsAdapter.notifyDataSetChanged()
                Log.i(TAG, "Number of photos: ${listMarsPhotos.size}")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to load Mars photos", e)
            }
        }
    }

    fun getJson(view: View) {
        getMarsPhotos()
    }
}
