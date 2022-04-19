package com.modyo.pokedex.challenge.pokemon.domain;

import com.modyo.pokedex.challenge.pokemon.infrastucture.PokemonRepository;
import org.springframework.stereotype.Service;

@Service
public class PokemonFinderServices {
    private PokemonRepository pokemonRepository;
    PokemonFinderServices (PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }
    public PokemonResponseData pokemonFinder(String name){
        return pokemonRepository.finderPokemon(name);
    }

}
