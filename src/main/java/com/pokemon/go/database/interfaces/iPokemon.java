package com.pokemon.go.database.interfaces;

import java.util.List;

import com.pokemon.go.database.model.BaseStat;
import com.pokemon.go.database.model.Info;
import com.pokemon.go.database.model.Physique;
import com.pokemon.go.database.model.SpecialMove;

public interface iPokemon {

	String getPokemonName();

	void setPokemonName(String pokemonName);

	Info getInfo();

	void setInfo(Info info);

	BaseStat getBaseStat();

	void setBaseStat(BaseStat baseStat);

	Physique getPhysique();

	void setPhysique(Physique physique);

	List<SpecialMove> getSpecialMove();

	void setSpecialMove(List<SpecialMove> specialMoves);
}
