package br.com.fiap.pokedex.api

import br.com.fiap.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokemonAPI{

    @GET("/api/pokemon")

    //IMPORTE A CALL do RETROFIT
    //Vai fazer uma call para retornar meu Pokemons que busquei na API
    fun listaPokemons(): Call<PokemonResponse>

}