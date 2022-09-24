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
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView2.text = numberString
        presenter?.apiCall(numberString!!)
        val plusBtn = findViewById<Button>(R.id.plusBtn)
        plusBtn.setOnClickListener {
            number += 1;
            numberString = number.toString();
            println(numberString)
            presenter?.apiCall(numberString!!)
        }
        val minusBtn = findViewById<Button>(R.id.minusBtn)
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
        val textView1 = findViewById<TextView>(R.id.textView1)
        textView1.text = numberString + " " + presenter?.showPokemonName()
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView2.text = numberString
    }
}