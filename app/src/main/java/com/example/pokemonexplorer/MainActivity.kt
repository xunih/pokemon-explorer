package com.example.pokemonexplorer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
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
        presenter?.changeMinusBtn(false)
        presenter = PokemonPresenter(this)
        val textView2 = findViewById<EditText>(R.id.textView2)
        textView2.setText(numberString)
        presenter?.apiCall(numberString!!)
        val plusBtn = findViewById<Button>(R.id.plusBtn)
        plusBtn.setOnClickListener {
            number += 1;
            presenter?.changeMinusBtn(true)
            numberString = number.toString();
            presenter?.apiCall(numberString!!)
        }
        val minusBtn = findViewById<Button>(R.id.minusBtn)
        minusBtn.setOnClickListener {
            if (number !== 1) {
                number -= 1;
                numberString = number.toString();
                presenter?.apiCall(numberString!!)
            }
            if (number == 1) {
                presenter?.changeMinusBtn(false)
            }
        }

        textView2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val text = p0.toString()
                if (!text.isNullOrBlank() &&
                    !text.isEmpty() &&
                    !text.isNullOrEmpty() &&
                    text.isDigitsOnly() &&
                    text.toIntOrNull() !== null
                ) {
                    if (text == numberString) {
                    } else {
                        numberString = text;
                        number = text.toInt()
                        presenter?.apiCall(numberString!!)
                        if (number !== 1) {
                            presenter?.changeMinusBtn(true)
                        } else {
                            presenter?.changeMinusBtn(false)
                        }
                    }
                }

            }
        })
    }

    override fun updateViewData() {
        val minusBtn = findViewById<Button>(R.id.minusBtn)
        var minusBtnStatus = presenter?.getBtnStatus()
        if (minusBtnStatus != null) {
            minusBtn.isEnabled = minusBtnStatus
        }
        val textView1 = findViewById<TextView>(R.id.textView1)
        textView1.text =
            (presenter?.showPokemonDetails()?.second) + " " + (presenter?.showPokemonDetails()?.first)
        val textView2 = findViewById<EditText>(R.id.textView2)
        textView2.setText(presenter?.showPokemonDetails()?.second)
        val imageView = findViewById<ImageView>(R.id.imageView)
        img_url =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + presenter?.showPokemonDetails()?.second + ".png"
        Picasso.get().load(img_url).into(imageView)
    }

    override fun switchToNoDataView() {
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE

    }

    override fun switchBack() {
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
    }
}