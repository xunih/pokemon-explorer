package com.example.pokemonexplorer.model.api

import PokemonItem
import Results
import com.example.pokemonexplorer.interfaces.Constant
import com.example.pokemonexplorer.interfaces.Constant.Companion.NUMBER
import com.example.pokemonexplorer.interfaces.PokemonInterface
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Path

interface APIInterface : PokemonInterface {
    @GET(NUMBER)
    fun getPokemonName(@Path("id") id: String): Call<Results>
}