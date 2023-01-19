package com.debug.pokedex.presenter.home

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.debug.pokedex.R
import com.debug.pokedex.databinding.FragmentMainBinding
import com.debug.pokedex.presenter.home.adapter.PokemonAdapter
import com.debug.pokedex.presenter.home.model.PokemonListScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onResume() {
        super.onResume()
        viewModel.getPokemonList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
    }

    private fun setObservers() {
        viewModel.pokemons.observe(requireActivity()) {
            binding.pokemons.adapter = PokemonAdapter(it) { pokemon ->
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(pokemon)
                binding.root.findNavController().navigate(action)
            }
        }

        viewModel.screenState.observe(requireActivity()) {
            setViewState(it)
        }
    }

    private fun setViewState(state: PokemonListScreenState) {
        when (state) {
            PokemonListScreenState.LOADING -> loadingContent()
            PokemonListScreenState.CONTENT -> showContent()
        }
    }

    private fun showContent() {
        binding.loadingImage.isVisible = false
        binding.pokemons.isVisible = true
    }

    private fun loadingContent() {
        val imageLoader = ImageLoader.Builder(requireContext())
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        binding.loadingImage.load(
             R.drawable.pikachu_running,
            imageLoader = imageLoader
        )

        binding.loadingImage.isVisible = true
        binding.pokemons.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}