package com.modyo.pokedex.challenge.pokemon.domain;

import com.modyo.pokedex.challenge.pokemon.infrastucture.PokemonRepository;
import org.springframework.stereotype.Service;

@Service
public class PokemonAllServices {

    PokemonRepository pokemonRepository;
    PokemonAllServices(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }
    public PokemonPaginationResponseData pokemonGetAll(Integer offset, Integer limit){
        return pokemonRepository.finderAllPokemon(offset, limit);
    }

}
