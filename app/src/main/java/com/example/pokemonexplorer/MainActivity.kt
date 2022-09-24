package com.example.pokemonexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.pokemonexplorer.interfaces.PokemonInterface
import com.example.pokemonexplorer.presenter.PokemonPresenter

class MainActivity : AppCompatActivity(), PokemonInterface.PokemonView {
    private var number = 0
    private var presenter: PokemonPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = PokemonPresenter(this)
        val plusBtn = findViewById(R.id.plusBtn) as Button
        plusBtn.setOnClickListener {
            println("Number is ")
            number = number + 1;
            println(number);
            var num = number.toString();
            println("Num id is ")
            println(num)
            presenter?.apiCall(num!!)
        }
    }

    override fun updateViewData() {
        val textView1 = findViewById(R.id.textView1) as TextView
        textView1.text = presenter?.showPokemonName()
    }
}