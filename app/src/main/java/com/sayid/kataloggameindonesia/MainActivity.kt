package com.sayid.kataloggameindonesia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        setSupportActionBar(binding.toolbar)
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
        listGameAdapter.setOnClickCallback(
            object : ListGameAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Game) {
                    val intentToDetail = Intent(this@MainActivity, DetailGame::class.java)
                    intentToDetail.putExtra(DetailGame.GAME_DATA, data)
                    startActivity(intentToDetail)
                }
            },
        )
        binding.rvGame.adapter = listGameAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intentToAbout = Intent(this@MainActivity, AboutPage::class.java)
                startActivity(intentToAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
