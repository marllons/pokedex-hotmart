package com.debug.pokedex.domain.entity

enum class PokemonType(val type: String) {
    BUG("bug"),
    DARK("dark"),
    DRAGON("dragon"),
    ELECTRIC("electric"),
    FAIRLY("fairly"),
    FIGHTING("fighting"),
    FIRE("fire"),
    FLYING("flying"),
    GHOST("ghost"),
    GRASS("grass"),
    GROUND("ground"),
    ICE("ice"),
    NORMAL("normal"),
    POISON("poison"),
    PSYCHIC("psychic"),
    ROCK("rock"),
    STEEL("steel"),
    WATER("water");

    companion object {
        fun fromString(type: String): PokemonType {
            return when (type) {
                BUG.type -> BUG
                DARK.type -> DARK
                DRAGON.type -> DRAGON
                ELECTRIC.type -> ELECTRIC
                FAIRLY.type -> FAIRLY
                FIGHTING.type -> FIGHTING
                FIRE.type -> FIRE
                FLYING.type -> FLYING
                GHOST.type -> GHOST
                GRASS.type -> GRASS
                GROUND.type -> GROUND
                ICE.type -> ICE
                NORMAL.type -> NORMAL
                POISON.type -> POISON
                PSYCHIC.type -> PSYCHIC
                ROCK.type -> ROCK
                STEEL.type -> STEEL
                else -> WATER
            }
        }
    }
}