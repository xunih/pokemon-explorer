package com.example.pokemonexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.pokemonexplorer.interfaces.PokemonInterface
import com.example.pokemonexplorer.presenter.PokemonPresenter
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), PokemonInterface.PokemonView {
    private var number = 1
    private var numberString = "1"
    private var presenter: PokemonPresenter? = null
    private var img_url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = PokemonPresenter(this)
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView2.text = numberString
        presenter?.apiCall(numberString!!)
        val imageView = findViewById<ImageView>(R.id.imageView)
        img_url =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numberString.png"
        Picasso.get().load(img_url).into(imageView)
        val plusBtn = findViewById<Button>(R.id.plusBtn)
        plusBtn.setOnClickListener {
            number += 1;
            numberString = number.toString();
            presenter?.apiCall(numberString!!)
            img_url =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numberString.png"
            Picasso.get().load(img_url).into(imageView)
        }
        val minusBtn = findViewById<Button>(R.id.minusBtn)
        minusBtn.setOnClickListener {
            if (number !== 1) {
                number -= 1;
                numberString = number.toString();
                presenter?.apiCall(numberString!!)
                img_url =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$numberString.png"
                Picasso.get().load(img_url).into(imageView)
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