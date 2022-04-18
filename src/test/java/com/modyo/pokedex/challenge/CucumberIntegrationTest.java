package com.modyo.pokedex.challenge;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty",features = "src/test/resources/features/",publish = true,glue = {
        "com.modyo.pokedex.challenge"})
public class CucumberIntegrationTest {

}