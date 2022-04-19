package com.modyo.pokedex.challenge.pokemon.application;

import com.modyo.pokedex.challenge.pokemon.domain.PokemonFinderServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonFinderGetController {

    PokemonFinderServices pokemonFinderServices;

    PokemonFinderGetController(PokemonFinderServices pokemonFinderServices){
        this.pokemonFinderServices    = pokemonFinderServices;
    }

    @GetMapping(value = "/{name}")
    @ResponseBody
    public ResponseEntity<Object> getPokemonFinder(@PathVariable String name) {
        return ResponseHandler.generateResponse(true,"Request pokemon  for name", HttpStatus.OK,pokemonFinderServices.pokemonFinder(name));
    }
}
