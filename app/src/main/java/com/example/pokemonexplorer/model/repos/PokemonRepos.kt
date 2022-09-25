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
    private var pokemonId = "1"

    init {
        apiclient = PokemonAPI.client.create(APIInterface::class.java)
    }

    override fun getPokemonById(
        id: String,
        presenter: PokemonInterface.PokemonPresenter
    ) {
        val call = apiclient?.getPokemonDetails(id)
        call?.enqueue(object : Callback<Results> {
            override fun onFailure(call: Call<Results>, t: Throwable) {
                Log.d("error", t.toString())
            }

            override fun onResponse(
                call: Call<Results>,
                response: Response<Results>
            ) {
                if (response.isSuccessful) {
                    var resultName = response.body()?.name
                    var resultId = response.body()?.id
                    if (resultName != null && resultId != null) {
                        pokeName = resultName
                        pokemonId = resultId
                    }
                    presenter.UIAutoUpdate()
                }
            }
        })
    }

    override fun getPokemonDetails(): Pair<String, String> {
        println("hey")
        println(Pair(pokeName, pokemonId).first)
        println(Pair(pokeName, pokemonId).second)
        return Pair(pokeName, pokemonId);
    }

}