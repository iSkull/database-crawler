package com.pokemon.go.database.enums;

public enum PokemonEggEnum {
	SHORT(2), MEDIUM(5), LONG(10);

	private int value;

	PokemonEggEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
