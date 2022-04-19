package com.modyo.pokedex.challenge.pokemon.infrastucture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modyo.pokedex.challenge.pokemon.domain.PokemonResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class PokemonFinderPokeApiRepository implements PokemonFinderRepository{
    Logger logger = LoggerFactory.getLogger(PokemonFinderPokeApiRepository.class);
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public PokemonResponseData finderPokemon(String name) {
        final String uri = "https://pokeapi.co/api/v2/pokemon/" + name;
        RestTemplate restTemplate = new RestTemplate();
        PokemonResponseData pokemonData = new PokemonResponseData();
        try {

            String result = restTemplate.getForObject(uri, String.class);
            JsonNode jsonNode = mapper.readTree(result);

            pokemonData.setName(jsonNode.get("name").asText());
            pokemonData.setWeight(jsonNode.get("weight").asInt());
            pokemonData.setWeight(jsonNode.get("weight").asInt());
            pokemonData.setType(pokemonData.cleanTypes(jsonNode.get("types")));
            pokemonData.setAbilities(pokemonData.cleanAbilities(jsonNode.get("abilities")));
            pokemonData.setPhoto(pokemonData.cleanPhoto(jsonNode.get("sprites")));

            return  pokemonData;
        }catch (Exception err){
            logger.error(err.getLocalizedMessage());
        }
        return pokemonData;
    }
}
