package com.example.pokemonexplorer.interfaces

import android.text.BoringLayout
import com.example.pokemonexplorer.presenter.PokemonPresenter

interface PokemonInterface {
    interface PokemonModel {
        fun getPokemonById(
            id: String,
            presenter: PokemonPresenter
        )

        fun getPokemonDetails(): Pair<String, String>

        fun setMinusBtn(on: Boolean, presenter: PokemonPresenter)

        fun getMinusBtn(): Boolean
    }

    interface PokemonView {
        fun updateViewData()
        fun switchToNoDataView()
        fun switchBack()
    }

    interface PokemonPresenter {
        fun apiCall(id: String)
        fun showPokemonDetails(): Pair<String, String>
        fun UIAutoUpdate()
        fun changeMinusBtn(on: Boolean)
        fun getBtnStatus(): Boolean
        fun changeView()
        fun changeViewBack()
    }
}