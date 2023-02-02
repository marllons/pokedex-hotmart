package com.debug.pokedex.presenter.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.debug.pokedex.Constants.VIEW_TYPE_NET
import com.debug.pokedex.Constants.VIEW_TYPE_POKE
import com.debug.pokedex.databinding.ItemPokemonBinding
import com.debug.pokedex.presenter.home.model.PokemonViewObject

class PokemonPagingAdapter(
    private val context: Context,
    private val onClickItem: (PokemonViewObject) -> Unit
) : PagingDataAdapter<PokemonViewObject, PokemonPagingAdapter.PokemonViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        pokemon?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            ItemPokemonBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            VIEW_TYPE_NET
        } else {
            VIEW_TYPE_POKE
        }
    }

    inner class PokemonViewHolder(
        private val binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: PokemonViewObject) {
            binding.apply {
                val pokemonIdFormatted = "#${pokemon.id.toString().padStart(3, '0')}"
                pokemonId.text = pokemonIdFormatted
                pokemonName.text = pokemon.name.replaceFirstChar(Char::titlecase)
                pokemonImage.load(pokemon.image)

                background.backgroundTintList =
                    ContextCompat.getColorStateList(itemView.context, (pokemon.mainType.color))

                firstType.root.isVisible = false
                pokemon.types.firstOrNull()?.also {
                    firstType.root.apply {
                        isVisible = true
                        backgroundTintList =
                            ContextCompat.getColorStateList(itemView.context, it.colorAccent)

                    }
                    firstType.typeIcon.setImageResource(it.icon)
                    firstType.typeDescription.setText(it.type)
                }

                secondType.root.isVisible = false
                pokemon.types.getOrNull(1)?.also {
                    secondType.root.apply {
                        visibility = View.VISIBLE
                        backgroundTintList =
                            ContextCompat.getColorStateList(itemView.context, it.colorAccent)
                    }

                    secondType.typeIcon.setImageResource(it.icon)
                    secondType.typeDescription.setText(it.type)
                }

                itemView.setOnClickListener {
                    onClickItem(pokemon)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PokemonViewObject>() {
            override fun areItemsTheSame(oldItem: PokemonViewObject, newItem: PokemonViewObject) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: PokemonViewObject, newItem: PokemonViewObject) = oldItem == newItem
        }
    }

}