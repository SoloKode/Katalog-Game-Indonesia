package com.sayid.kataloggameindonesia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sayid.kataloggameindonesia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvGame.setHasFixedSize(true)

        list.addAll(getListGame())
        showRecyclerList()
    }

    private fun getListGame(): ArrayList<Game> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listGame = ArrayList<Game>()

        for (i in dataName.indices) {
            val game =
                Game(
                    dataName[i],
                    dataGenre[i],
                    dataDescription[i],
                    dataPhoto.getResourceId(i, -1),
                )
            listGame.add(game)
        }
        return listGame
    }

    private fun showRecyclerList() {
        binding.rvGame.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListGameAdapter(list)
        binding.rvGame.adapter = listGameAdapter
    }
}
