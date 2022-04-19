package com.modyo.pokedex.challenge.pokemon.infrastucture;

import com.modyo.pokedex.challenge.pokemon.domain.PokemonPaginationResponseData;
import com.modyo.pokedex.challenge.pokemon.domain.PokemonResponseData;

public interface PokemonRepository {
    PokemonResponseData finderPokemon(String name);
    PokemonPaginationResponseData finderAllPokemon(Integer offset, Integer limit);

}
