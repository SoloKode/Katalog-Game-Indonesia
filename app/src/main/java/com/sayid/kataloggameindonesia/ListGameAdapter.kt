package com.sayid.kataloggameindonesia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sayid.kataloggameindonesia.databinding.ItemRowGameBinding

class ListGameAdapter(
    private val listGame: ArrayList<Game>,
) : RecyclerView.Adapter<ListGameAdapter.ListViewHolder>() {
    class ListViewHolder(
        var binding: ItemRowGameBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder {
        val binding = ItemRowGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listGame.size

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int,
    ) {
        val (name, genre, description, photo) = listGame[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvGenre.text = genre
        holder.binding.tvItemDescription.text = description
    }
}
