package com.debug.pokedex.presenter.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.debug.pokedex.R
import com.debug.pokedex.databinding.LayoutMoreItemsLoadingBinding
import com.debug.pokedex.utils.extensions.imageLoaderBuild

class PokemonLoaderPagingAdapter : LoadStateAdapter<PokemonLoaderPagingViewHolder>() {

    override fun onBindViewHolder(holder: PokemonLoaderPagingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        PokemonLoaderPagingViewHolder.create(parent)
}

class PokemonLoaderPagingViewHolder(
    private val binding: LayoutMoreItemsLoadingBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        when(loadState) {
            is LoadState.Loading -> {
               binding.root.context.imageLoaderBuild { imageLoader ->
                   binding.loadingImage.load(
                       R.drawable.pikachu_running,
                       imageLoader = imageLoader
                   )
               }
                binding.loadingImage.isVisible = true
            }
            else -> {
                binding.loadingImage.isVisible = false
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PokemonLoaderPagingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_more_items_loading, parent, false)
            return PokemonLoaderPagingViewHolder(LayoutMoreItemsLoadingBinding.bind(view))
        }
    }
}