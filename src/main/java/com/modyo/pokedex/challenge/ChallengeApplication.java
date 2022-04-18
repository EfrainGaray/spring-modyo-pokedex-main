package com.modyo.pokedex.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {


	private int value;
	public void sum(int aNumber, int anotherNumber){
		value = aNumber + anotherNumber;
	}
	public void subtract(int aNumber, int anotherNumber) {
		value = aNumber - anotherNumber;
	}

	public int currentValue(){
		return value;
	}
}
