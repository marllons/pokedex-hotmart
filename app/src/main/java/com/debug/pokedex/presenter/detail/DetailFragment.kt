package com.debug.pokedex.presenter.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import coil.load
import com.debug.pokedex.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPokemonInfo()
    }

    private fun setPokemonInfo() {
        val pokemon = args.pokemon
        with(binding) {

            pokemonImage.load(pokemon.image)
            pokemonId.text = "#${pokemon.id.toString().padStart(3, '0')}"

            pokemonName.text = pokemon.name.replaceFirstChar(Char::titlecase)
            pokemonImage.load(pokemon.image)


            firstType.root.isVisible = false
            pokemon.types.firstOrNull()?.also {
                firstType.root.apply {
                    isVisible = true
                    backgroundTintList =
                        ContextCompat.getColorStateList(requireContext(), it.colorAccent)

                }
                firstType.typeIcon.setImageResource(it.icon)
                firstType.typeDescription.setText(it.type)
            }

            secondType.root.isVisible = false
            pokemon.types.getOrNull(1)?.also {
                secondType.root.apply {
                    visibility = View.VISIBLE
                    backgroundTintList =
                        ContextCompat.getColorStateList(requireContext(), it.colorAccent)
                }

                secondType.typeIcon.setImageResource(it.icon)
                secondType.typeDescription.setText(it.type)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}