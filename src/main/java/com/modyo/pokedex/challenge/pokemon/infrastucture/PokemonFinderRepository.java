package com.modyo.pokedex.challenge.pokemon.infrastucture;

import com.modyo.pokedex.challenge.pokemon.domain.PokemonResponseData;

public interface PokemonFinderRepository {
    PokemonResponseData finderPokemon(String name);
}
