package com.example.pokemonexplorer.model.api

import com.example.pokemonexplorer.interfaces.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonAPI {
    companion object {
        private var retrofit: Retrofit? = null

        val client: Retrofit
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Constant.BASE_URL)
                        .build()
                }
                return retrofit!!
            }
    }
}