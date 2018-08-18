package br.com.fiap.pokedex

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.fiap.pokedex.api.getPokemonApi
import br.com.fiap.pokedex.model.Pokemon
import br.com.fiap.pokedex.model.PokemonResponse
import kotlinx.android.synthetic.main.activity_lista.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        //Chamando minha função para popular
        getPokemons()

        rvPokemons.layoutManager = LinearLayoutManager(this)
    }

    fun exibeMensagemErro(t: Throwable?) {
        Toast.makeText(this, t?.message, Toast.LENGTH_LONG).show()
    }

    //Vou exibir na lista minha lista de pokemons que vou receber
    fun exibeNaLista(pokemons: List<Pokemon>) {
        rvPokemons.adapter = ListaPokemonAdapter(pokemons,
                this, {
            val telaDetalhe = Intent(this,
                    DetalheActivity::class.java)

            telaDetalhe.putExtra("POKEMON", it)
            startActivity(telaDetalhe)
        })
    }

    //Vamos retornar nossos Pokemons
    fun getPokemons() {
        //enqueue faz a chamada em uma trad separada
        //vou pegar minha função que criei para pegar os dados da api, vou pegar a minha ListaPokemons, vou por em uma enqueue
        //vou retonar um objeto de callback do RETROFIT, o meu PokemonResponse
        //DOU ALT+ENTER NO Object implementando o onFailure onResponse
        getPokemonApi().listaPokemons().enqueue(object : Callback<PokemonResponse> {
            override fun onFailure(call: Call<PokemonResponse>?, t: Throwable?) {
                exibeMensagemErro(t)
            }

            override fun onResponse(call: Call<PokemonResponse>?, response: Response<PokemonResponse>?) {
                //Se deu bom foi sucesso
                if (response!!.isSuccessful) {
                    //Pega minha função que exibe Nalista e passo o meu val Content la do meu objeto
                    exibeNaLista(response?.body()!!.content)
                }
            }
        })
    }

}
