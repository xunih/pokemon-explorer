package com.example.pokemonexplorer.model.repos

import Results
import android.util.Log
import  retrofit2.Callback
import com.example.pokemonexplorer.interfaces.PokemonInterface
import com.example.pokemonexplorer.model.api.APIInterface
import com.example.pokemonexplorer.model.api.PokemonAPI
import retrofit2.Call
import retrofit2.Response

class PokemonRepos : PokemonInterface.PokemonModel {
    private var pokeName = "test"
    private var apiclient: APIInterface? = null

    init {
        apiclient = PokemonAPI.client.create(APIInterface::class.java)
    }

    override fun getPokemonNameById(
        id: String,
        presenter: PokemonInterface.PokemonPresenter
    ) {
        val call = apiclient?.getPokemonName(id)
        call?.enqueue(object : Callback<Results> {
            override fun onFailure(call: Call<Results>, t: Throwable) {
                Log.d("error", t.toString())
            }

            override fun onResponse(
                call: Call<Results>,
                response: Response<Results>
            ) {
                if (response.isSuccessful) {
                    var results = response.body()?.name
                    if (results != null) {
                        pokeName = results
                    }
                    presenter.UIAutoUpdate()
                }
            }
        })
    }

    override fun getPokemonName(): String {
        return pokeName;
    }

}