package com.example.pokemonexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.pokemonexplorer.interfaces.PokemonInterface
import com.example.pokemonexplorer.presenter.PokemonPresenter

class MainActivity : AppCompatActivity(), PokemonInterface.PokemonView {
    private var number = 1
    private var numberString = "1"
    private var presenter: PokemonPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = PokemonPresenter(this)
        val textView2 = findViewById(R.id.textView2) as TextView
        textView2.text = numberString
        presenter?.apiCall(numberString!!)
        val plusBtn = findViewById(R.id.plusBtn) as Button
        plusBtn.setOnClickListener {
            number += 1;
            numberString = number.toString();
            println(numberString)
            presenter?.apiCall(numberString!!)
        }
        val minusBtn = findViewById(R.id.minusBtn) as Button
        minusBtn.setOnClickListener {
            if (number !== 1) {
                number -= 1;
                numberString = number.toString();
                println(numberString)
                presenter?.apiCall(numberString!!)
            }
        }
    }

    override fun updateViewData() {
        val textView1 = findViewById(R.id.textView1) as TextView
        textView1.text = presenter?.showPokemonName()
        val textView2 = findViewById(R.id.textView2) as TextView
        textView2.text = numberString
    }
}