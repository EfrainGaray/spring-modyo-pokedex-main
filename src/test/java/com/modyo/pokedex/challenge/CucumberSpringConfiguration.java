package com.modyo.pokedex.challenge;

import com.modyo.pokedex.challenge.pokemon.application.HealthCheckGetController;
import com.modyo.pokedex.challenge.pokemon.application.PokemonFinderGetController;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = {ChallengeApplication.class, HealthCheckGetController.class, PokemonFinderGetController.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {ChallengeApplication.class, HealthCheckGetController.class, PokemonFinderGetController.class}, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

}