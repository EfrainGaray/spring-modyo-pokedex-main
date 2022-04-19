package com.modyo.pokedex.challenge.pokemon.application;

import com.modyo.pokedex.challenge.pokemon.domain.PokemonAllServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon")
public class PokemonAllGetController {

    PokemonAllServices pokemonAllServices;

    PokemonAllGetController(PokemonAllServices pokemonAllServices){
        this.pokemonAllServices = pokemonAllServices;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Object> getAllPokemon(@RequestParam(required = false) Integer offset,@RequestParam(required = false) Integer limit) {

        return ResponseHandler.generateResponse(true,"Request all pokemon for pages", HttpStatus.OK,pokemonAllServices.pokemonGetAll(offset, limit) );
    }

}
