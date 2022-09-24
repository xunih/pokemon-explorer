package com.example.pokemonexplorer.presenter

import com.example.pokemonexplorer.interfaces.PokemonInterface
import com.example.pokemonexplorer.model.repos.PokemonRepos

class PokemonPresenter(pokemonview: PokemonInterface.PokemonView) :
    PokemonInterface.PokemonPresenter {
    private var view: PokemonInterface.PokemonView = pokemonview
    private var model: PokemonInterface.PokemonModel = PokemonRepos()

    override fun apiCall(id: String) {
        model?.getPokemonNameById(id, this)
    }

    override fun showPokemonName(): String {
        return model.getPokemonName()
    }

    override fun UIAutoUpdate() {
        view.updateViewData()
    }

}