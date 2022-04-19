package com.modyo.pokedex.challenge.pokemon.domain;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PokemonResponseData {
    private String name;
    private String type;
    private String photo;
    private int weight;
    private ArrayList abilities;

    public PokemonResponseData(){

    }

    public PokemonResponseData(String name, String type, String photo, int weight, ArrayList abilities) {
        this.name = name;
        this.type = type;
        this.photo = photo;
        this.weight = weight;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList abilities) {
        this.abilities = abilities;
    }

    public String cleanTypes(@NotNull JsonNode types){
        String typesJoin = "";
        if (types.isArray()) {
            int x = 0;
            for (final JsonNode type : types) {
                if(x == 0 && types.size() > 1){
                    typesJoin+= type.get("type").get("name") + "/";
                }
                else{
                    typesJoin+= type.get("type").get("name");
                }
                x++;

            }
            typesJoin =  typesJoin.replaceAll("\"","");
        }
        return typesJoin;
    }
    public ArrayList cleanAbilities(@NotNull JsonNode abilities){
        ArrayList abilitiesJoin = new ArrayList();
        if (abilities.isArray()) {
            for (final JsonNode ability : abilities) {
                abilitiesJoin.add(ability.get("ability").get("name").asText());
            }
        }
      return abilitiesJoin;
    }
    public String cleanPhoto(@NotNull JsonNode sprites){
        return photo =  sprites.get("other").get("official-artwork").get("front_default").asText();
    }

    @Override
    public String toString() {
        return "PokemonResponseData{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", photo='" + photo + '\'' +
                ", weight=" + weight +
                ", abilities=" + abilities +
                '}';
    }

}


