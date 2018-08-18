package br.com.fiap.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.pokedex.model.Pokemon

class DetalheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        val pokemon = intent.extras.getParcelable<Pokemon>("POKEMON")
        Toast.makeText(this, pokemon.nome, Toast.LENGTH_LONG).show()
    }
}
