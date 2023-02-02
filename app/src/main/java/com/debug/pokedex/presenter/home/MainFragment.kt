package com.debug.pokedex.presenter.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.paging.LoadState
import coil.load
import com.debug.pokedex.R
import com.debug.pokedex.databinding.FragmentMainBinding
import com.debug.pokedex.presenter.home.adapter.PokemonLoaderPagingAdapter
import com.debug.pokedex.presenter.home.adapter.PokemonPagingAdapter
import com.debug.pokedex.utils.extensions.imageLoaderBuild
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()
    private val pokemonPagingAdapter by lazy {
        PokemonPagingAdapter(requireContext()) { pokemon ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(pokemon)
            binding.root.findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState ?: run { viewModel.getPokemonList() }
        setConfigAdapter()
        setObservers()
    }

    private fun setConfigAdapter() {
        binding.pokemons.adapter = pokemonPagingAdapter.withLoadStateFooter(PokemonLoaderPagingAdapter())
        initialViewState()
    }

    private fun setObservers() {
        viewModel.pokemons.observe(requireActivity()) { flowLiveData ->
            lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                    flowLiveData.collectLatest {
                        pokemonPagingAdapter.submitData(it)
                    }
                }
            }
        }
    }

    private fun initialViewState() {
        lifecycleScope.launch {
            pokemonPagingAdapter.loadStateFlow.collectLatest { state ->
                when (state.refresh) {
                    is LoadState.NotLoading -> showContent()
                    LoadState.Loading -> loadingContent()
                    is LoadState.Error -> {}
                }
            }
        }
    }

    private fun showContent() {
        binding.loadingImage.isVisible = false
        binding.pokemons.isVisible = true
    }

    private fun loadingContent() {
        requireContext().imageLoaderBuild { imageLoader ->
            binding.loadingImage.load(
                R.drawable.pikachu_running,
                imageLoader = imageLoader
            )

            binding.loadingImage.isVisible = true
            binding.pokemons.isVisible = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}