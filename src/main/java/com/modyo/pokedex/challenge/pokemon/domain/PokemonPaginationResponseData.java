package com.modyo.pokedex.challenge.pokemon.domain;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokemonPaginationResponseData {
    private int count;
    private String next;
    private String previous;
    private List<PokemonResult> results;

    public PokemonPaginationResponseData(int count, String next, String previous, List<PokemonResult> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public PokemonPaginationResponseData() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PokemonResult> getResults() {
        return results;
    }

    public void setResults(List<PokemonResult> results) {
        this.results = results;
    }

    public String requestOffsetAndLimit (String url) {

        String offset = url.replaceAll("^.*?(?:\\?|&)offset=(\\d+)(?:&|$).*$", "$1") ;
        String limit =  url.replaceAll("^.*?(?:\\?|&)limit=(\\d+)(?:&|$).*$", "$1") ;

        if(offset.equals("null")|| limit.equals("null")){
            return "";
        }
        return "?offset=" + offset + "&limit=" + limit;

    }

    @Override
    public String toString() {
        return "PokemonPaginationResponseData{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous + '\'' +
                ", results=" + results +
                '}';
    }
}
