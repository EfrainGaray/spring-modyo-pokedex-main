package com.modyo.pokedex.challenge.pokemon.application;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class HealthCheckGetControllerStep {
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

    @Given("I send a request to the URL {string}")
    public void iSendARequest(String endpoint) throws Throwable {
        validatableResponse = requestSpecification().contentType(ContentType.JSON)
                .when().get(endpoint).then();
    }

    @Then("the response will return status {int}")
    public void extractResponse(int status) {
        validatableResponse.assertThat().statusCode(equalTo(status));
    }
}
