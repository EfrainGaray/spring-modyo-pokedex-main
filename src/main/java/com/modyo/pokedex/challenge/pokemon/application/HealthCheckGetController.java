package com.modyo.pokedex.challenge.pokemon.application;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class HealthCheckGetController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void index() {

    }
    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public void status() {

    }
}
