package com.modyo.pokedex.challenge.pokemon.application;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class PokemonFinderGetControllerStep {
    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

    private ValidatableResponse validatableResponse;

    private void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }


    protected RequestSpecification requestSpecification() {
        configureRestAssured();
        return given();
    }

    @Given("I send name a request to the URL {string}")
    public void iSendNameARequestPokemonFinder(String endpoint) throws Throwable {
        validatableResponse = requestSpecification().contentType(ContentType.JSON)
                .when().get(endpoint).then();
    }

    @When("the response a pokemon url will return status {int}")
    public void extractResponsePokemonFinder(int status) {
        validatableResponse.assertThat().statusCode(equalTo(status));
    }

    @Then("response json pokemon finder url  with body:")
    public void response_json_pokemon_finder_url_with_body(String docString) {
        String jsonToString = docString.replaceAll("(\r\n|\n)", "");
        validatableResponse.assertThat().body(containsString(jsonToString));
    }

}
