package br.com.fiap.pokedex.model

//Aqui estamos fazendo uma classe que recebe nossa API os dados e retorna um content (variavel) retornando uma lista de pokemon
data class  PokemonResponse(
        val content: List<Pokemon>
)