package com.modyo.pokedex.challenge.pokemon.domain;


import com.modyo.pokedex.challenge.pokemon.infrastucture.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


@SpringBootTest
public class PokemonFinderServicesTest {


    @Autowired
    private PokemonRepository pokemonFinderRepository;
    @Test
    void contextLoads(){
        PokemonFinderServices pokeServices = new PokemonFinderServices(pokemonFinderRepository);
        String name = "ivysaur";
        String type = "grass/poison";
        String photo = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png";
        int weight = 130;
        ArrayList abilitiesJoin = new ArrayList();
        abilitiesJoin.add("overgrow");
        abilitiesJoin.add("chlorophyll");
        PokemonResponseData pokeMock = new PokemonResponseData(
            name, type,photo,weight,abilitiesJoin
        );
        PokemonResponseData pokeService = pokeServices.pokemonFinder(name);

        assertEquals(pokeService.toString(), pokeMock.toString());


    }
}
