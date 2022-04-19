package com.modyo.pokedex.challenge.pokemon.domain;

import com.modyo.pokedex.challenge.pokemon.infrastucture.PokemonFinderRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class PokemonFinderServices {
    private PokemonFinderRepository pokemonFinderRepository;
    PokemonFinderServices (PokemonFinderRepository pokemonFinderRepository){
        this.pokemonFinderRepository = pokemonFinderRepository;
    }
    public PokemonResponseData pokemonFinder(String name){
        return pokemonFinderRepository.finderPokemon(name);
    }

}
