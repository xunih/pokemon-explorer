package com.example.pokemonexplorer.interfaces

interface PokemonInterface {
    interface PokemonModel {
        fun getPokemonById(
            id: String,
            presenter: PokemonPresenter
        )

        fun getPokemonDetails(): Pair<String, String>
    }

    interface PokemonView {
        fun updateViewData()
    }

    interface PokemonPresenter {
        fun apiCall(id: String)
        fun showPokemonDetails(): Pair<String, String>
        fun UIAutoUpdate()
    }
}