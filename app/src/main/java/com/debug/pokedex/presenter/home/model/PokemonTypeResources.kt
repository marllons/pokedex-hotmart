package com.debug.pokedex.presenter.home.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.debug.pokedex.R
import com.debug.pokedex.domain.entity.PokemonType
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PokemonTypeResources(
    @StringRes val type: Int,
    @DrawableRes val icon: Int,
    @ColorRes val color: Int,
    @ColorRes val colorAccent: Int
) : Parcelable {
    BUG_RESOURCES(
        R.string.pokemon_type_bug,
        R.drawable.ic_bug,
        R.color.background_bug,
        R.color.bug
    ),
    DARK_RESOURCES(
        R.string.pokemon_type_dark,
        R.drawable.ic_dark,
        R.color.background_dark,
        R.color.dark
    ),
    DRAGON_RESOURCES(
        R.string.pokemon_type_dragon,
        R.drawable.ic_dragon,
        R.color.background_dragon,
        R.color.dragon
    ),
    ELECTRIC_RESOURCES(
        R.string.pokemon_type_electric,
        R.drawable.ic_electric,
        R.color.background_electric,
        R.color.electric
    ),
    FAIRLY_RESOURCES(
        R.string.pokemon_type_fairly,
        R.drawable.ic_fairly,
        R.color.background_fairly,
        R.color.fairly
    ),
    FIGHTING_RESOURCES(
        R.string.pokemon_type_fighting,
        R.drawable.ic_fighting,
        R.color.background_fighting,
        R.color.fighting
    ),
    FIRE_RESOURCES(
        R.string.pokemon_type_fire,
        R.drawable.ic_fire,
        R.color.background_fire,
        R.color.fire
    ),
    FLYING_RESOURCES(
        R.string.pokemon_type_flying,
        R.drawable.ic_flying,
        R.color.background_flying,
        R.color.flying
    ),
    GHOST_RESOURCES(
        R.string.pokemon_type_ghost,
        R.drawable.ic_ghost,
        R.color.background_ghost,
        R.color.ghost
    ),
    GRASS_RESOURCES(
        R.string.pokemon_type_grass,
        R.drawable.ic_grass,
        R.color.background_grass,
        R.color.grass
    ),
    GROUND_RESOURCES(
        R.string.pokemon_type_ground,
        R.drawable.ic_ground,
        R.color.background_ground,
        R.color.ground
    ),
    ICE_RESOURCES(
        R.string.pokemon_type_ice,
        R.drawable.ic_ice,
        R.color.background_ice,
        R.color.ice
    ),
    NORMAL_RESOURCES(
        R.string.pokemon_type_normal,
        R.drawable.ic_normal,
        R.color.background_normal,
        R.color.normal
    ),
    POISON_RESOURCES(
        R.string.pokemon_type_poison,
        R.drawable.ic_poison,
        R.color.background_poison,
        R.color.poison
    ),
    PSYCHIC_RESOURCES(
        R.string.pokemon_type_psychic,
        R.drawable.ic_psychic,
        R.color.background_psychic,
        R.color.psychic
    ),
    ROCK_RESOURCES(
        R.string.pokemon_type_rock,
        R.drawable.ic_rock,
        R.color.background_rock,
        R.color.rock
    ),
    STEEL_RESOURCES(
        R.string.pokemon_type_steel,
        R.drawable.ic_steel,
        R.color.background_steel,
        R.color.steel
    ),
    WATER_RESOURCES(
        R.string.pokemon_type_water,
        R.drawable.ic_water,
        R.color.background_water,
        R.color.water
    );

    companion object {
        fun fromPokemonType(type: PokemonType) = when (type) {
            PokemonType.BUG -> BUG_RESOURCES
            PokemonType.DARK -> DARK_RESOURCES
            PokemonType.DRAGON -> DRAGON_RESOURCES
            PokemonType.ELECTRIC -> ELECTRIC_RESOURCES
            PokemonType.FAIRLY -> FAIRLY_RESOURCES
            PokemonType.FIGHTING -> FIGHTING_RESOURCES
            PokemonType.FIRE -> FIRE_RESOURCES
            PokemonType.FLYING -> FLYING_RESOURCES
            PokemonType.GHOST -> GHOST_RESOURCES
            PokemonType.GRASS -> GRASS_RESOURCES
            PokemonType.GROUND -> GROUND_RESOURCES
            PokemonType.ICE -> ICE_RESOURCES
            PokemonType.NORMAL -> NORMAL_RESOURCES
            PokemonType.POISON -> POISON_RESOURCES
            PokemonType.PSYCHIC -> PSYCHIC_RESOURCES
            PokemonType.ROCK -> ROCK_RESOURCES
            PokemonType.STEEL -> STEEL_RESOURCES
            PokemonType.WATER -> WATER_RESOURCES
        }

        fun toPokemonType(type: PokemonTypeResources) = when (type) {
            BUG_RESOURCES -> PokemonType.BUG
            DARK_RESOURCES -> PokemonType.DARK
            DRAGON_RESOURCES -> PokemonType.DRAGON
            ELECTRIC_RESOURCES -> PokemonType.ELECTRIC
            FAIRLY_RESOURCES -> PokemonType.FAIRLY
            FIGHTING_RESOURCES -> PokemonType.FIGHTING
            FIRE_RESOURCES -> PokemonType.FIRE
            FLYING_RESOURCES -> PokemonType.FLYING
            GHOST_RESOURCES -> PokemonType.GHOST
            GRASS_RESOURCES -> PokemonType.GRASS
            GROUND_RESOURCES -> PokemonType.GROUND
            ICE_RESOURCES -> PokemonType.ICE
            NORMAL_RESOURCES -> PokemonType.NORMAL
            POISON_RESOURCES -> PokemonType.POISON
            PSYCHIC_RESOURCES -> PokemonType.PSYCHIC
            ROCK_RESOURCES -> PokemonType.ROCK
            STEEL_RESOURCES -> PokemonType.STEEL
            WATER_RESOURCES -> PokemonType.WATER
        }

    }
}