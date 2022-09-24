package com.example.pokemonexplorer.interfaces

interface PokemonInterface {
    interface PokemonModel {
        fun getPokemonNameById(
            id: String,
            presenter: PokemonPresenter
        )

        fun getPokemonName(): String
    }

    interface PokemonView {
        fun updateViewData()
    }

    interface PokemonPresenter {
        fun apiCall(id: String)
        fun showPokemonName(): String
        fun UIAutoUpdate()
    }
}