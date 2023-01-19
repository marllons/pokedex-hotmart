package com.debug.pokedex.presenter.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.debug.pokedex.databinding.ItemPokemonBinding
import com.debug.pokedex.presenter.home.model.PokemonViewObject

class PokemonAdapter(
    private val dataSet: List<PokemonViewObject>,
    private val onClickItem: (PokemonViewObject) -> Unit
) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(private val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClickItem(dataSet[adapterPosition])
            }
        }

        fun bind(pokemon: PokemonViewObject) {
            binding.apply {
                pokemonId.text = "#${pokemon.id.toString().padStart(3, '0')}"
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
            }
        }
    }
}
