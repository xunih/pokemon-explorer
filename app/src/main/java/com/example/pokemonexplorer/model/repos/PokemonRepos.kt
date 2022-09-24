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
        println(call)
        call?.enqueue(object : Callback<Results> {
            override fun onFailure(call: Call<Results>, t: Throwable) {
                Log.d("error", t.toString())
            }

            override fun onResponse(
                call: Call<Results>,
                response: Response<Results>
            ) {
                println("inside success 4")
                println(response)
                if (response.isSuccessful) {
                    println("inside success 1")
                    var results = response.body()?.name
                    println("inside success 2")
                    if (results != null) {
                        println("inside success 3")
                        println(results)
                        pokeName = results
                        println(pokeName)
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