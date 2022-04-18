package com.modyo.pokedex.challenge.pokemon.application;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HealthCheckGetController {
    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void ok() {

    }
}
