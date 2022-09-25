package com.example.pokemonexplorer.presenter

import com.example.pokemonexplorer.interfaces.PokemonInterface
import com.example.pokemonexplorer.model.repos.PokemonRepos

class PokemonPresenter(pokemonview: PokemonInterface.PokemonView) :
    PokemonInterface.PokemonPresenter {
    private var view: PokemonInterface.PokemonView = pokemonview
    private var model: PokemonInterface.PokemonModel = PokemonRepos()

    override fun apiCall(id: String) {
        model?.getPokemonById(id, this)
    }

    override fun showPokemonDetails(): Pair<String, String> {
        return model.getPokemonDetails()
    }

    override fun UIAutoUpdate() {
        view.updateViewData()
    }

    override fun changeMinusBtn(on: Boolean) {
        model?.setMinusBtn(on, this)
    }

    override fun getBtnStatus(): Boolean {
        return model.getMinusBtn()
    }

}