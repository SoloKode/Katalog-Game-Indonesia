package com.sayid.kataloggameindonesia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val name: String,
    val genre: String,
    val description: String,
    val photo: Int,
) : Parcelable
