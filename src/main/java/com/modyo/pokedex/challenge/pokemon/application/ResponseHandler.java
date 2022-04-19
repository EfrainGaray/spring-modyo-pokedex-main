package com.modyo.pokedex.challenge.pokemon.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj, Object metadataObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("status", status.value());
        attributes.put("message", message);
        attributes.put("attributes", responseObj);
        map.put("data", attributes);
        map.put("metadata", metadataObj);


        return new ResponseEntity<Object>(map,status);
    }
    public static ResponseEntity<Object> generateResponse(Boolean success, String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("message", message);
        attributes.put("success", success);
        attributes.put("status", status.value());
        attributes.put("attributes", responseObj);
        map.put("data", attributes);
        map.put("metadata", "{}");


        return new ResponseEntity<Object>(map,status);
    }
}
