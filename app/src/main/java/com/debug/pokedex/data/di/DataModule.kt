package com.debug.pokedex.data.di

import com.debug.pokedex.data.api.PokemonApi
import com.debug.pokedex.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun providesPokemonApi(
        serviceProvider: ServiceProvider
    ) = serviceProvider.getService(PokemonApi::class.java)
}