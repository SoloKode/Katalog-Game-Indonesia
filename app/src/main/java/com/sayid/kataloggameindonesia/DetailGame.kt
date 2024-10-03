package com.sayid.kataloggameindonesia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sayid.kataloggameindonesia.databinding.ActivityDetailGameBinding

class DetailGame : AppCompatActivity() {
    private lateinit var binding: ActivityDetailGameBinding
    private lateinit var gameData: Game

    companion object {
        const val GAME_DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        gameData =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getParcelableExtra(GAME_DATA, Game::class.java)!!
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(GAME_DATA)!!
            }

        title = gameData.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_32)

        binding.imgContentPhoto.setImageResource(gameData.photo)
        binding.imgContentPhoto.contentDescription = gameData.name
        binding.tvContentName.text = gameData.name
        binding.tvGenreContent.text = gameData.genre
        binding.tvGameDescriptionContent.text = gameData.description
        binding.tvDataPrice.text = gameData.price
        binding.tvDataDeveloper.text = gameData.developer
        binding.tvDataReleaseDate.text = gameData.releaseDate
        binding.tvDataPublisher.text = gameData.publisher
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.action_share -> {
                val message =
                    """
                    Cek game ini: ${gameData.name}
                    Genre: ${gameData.genre}
                    Deskripsi: ${gameData.description}
                    """.trimIndent()

                val intent =
                    Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, message)
                    }

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(Intent.createChooser(intent, "Kirim pesan melalui:"))
                } else {
                    Toast
                        .makeText(
                            this,
                            "Tidak ada aplikasi pesan yang tersedia",
                            Toast.LENGTH_SHORT,
                        ).show()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
}
