package com.modyo.pokedex.challenge.pokemon.infrastucture;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modyo.pokedex.challenge.pokemon.domain.PokemonPaginationResponseData;
import com.modyo.pokedex.challenge.pokemon.domain.PokemonResponseData;
import com.modyo.pokedex.challenge.pokemon.domain.PokemonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
public class PokemonFinderPokeApiRepository implements PokemonRepository {
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

    @Override
    public PokemonPaginationResponseData finderAllPokemon(Integer offset, Integer limit) {


        PokemonPaginationResponseData pokemonPaginationResponseData = new PokemonPaginationResponseData();

        String uri = "https://pokeapi.co/api/v2/pokemon";
        if(offset != null && limit != null){
            uri = uri + "?offset=" + offset + "&" + "limit=" + limit;
        }
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            JsonNode jsonNode = mapper.readTree(result);
            pokemonPaginationResponseData.setCount(jsonNode.get("count").asInt());
            String next = pokemonPaginationResponseData.requestOffsetAndLimit(jsonNode.get("next").asText());
            pokemonPaginationResponseData.setNext(next);
            String previous = pokemonPaginationResponseData.requestOffsetAndLimit(jsonNode.get("previous").asText());
            pokemonPaginationResponseData.setPrevious(previous);
            JsonNode results  = jsonNode.get("results");
            List<PokemonResult> pokeJoin = new ArrayList<PokemonResult>();
            if (results.isArray()) {
                int x = 0;
                for (final JsonNode pokemon : results) {
                    PokemonResult poke = new PokemonResult(pokemon.get("name").asText(),"/pokemon/" + pokemon.get("name").asText());
                    pokeJoin.add(poke);
                }
            }
            pokemonPaginationResponseData.setResults(pokeJoin);

            return  pokemonPaginationResponseData;
        }catch (Exception err){
            logger.error(err.getLocalizedMessage());
        }
        return pokemonPaginationResponseData;
    }
}
