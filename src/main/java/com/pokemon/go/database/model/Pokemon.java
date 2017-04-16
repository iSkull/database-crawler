package com.pokemon.go.database.model;

import java.util.List;

import javax.persistence.Id;

import com.pokemon.go.database.interfaces.iPokemon;

public class Pokemon implements iPokemon {

	@Id
	private String id;
	private String pokemonName;
	private Info info;
	private BaseStat baseStat;
	private Physique physique;
	private List<SpecialMove> specialMoves;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getPokemonName() {
		// TODO Auto-generated method stub
		return pokemonName;
	}

	@Override
	public void setPokemonName(String pokemonName) {
		// TODO Auto-generated method stub
		this.pokemonName = pokemonName;
	}

	@Override
	public Info getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setInfo(Info info) {
		// TODO Auto-generated method stub
		this.info = info;
	}

	@Override
	public BaseStat getBaseStat() {
		// TODO Auto-generated method stub
		return baseStat;
	}

	@Override
	public void setBaseStat(BaseStat baseStat) {
		// TODO Auto-generated method stub
		this.baseStat = baseStat;
	}

	@Override
	public Physique getPhysique() {
		// TODO Auto-generated method stub
		return physique;
	}

	@Override
	public void setPhysique(Physique physique) {
		// TODO Auto-generated method stub
		this.physique = physique;
	}

	@Override
	public List<SpecialMove> getSpecialMove() {
		// TODO Auto-generated method stub
		return specialMoves;
	}

	@Override
	public void setSpecialMove(List<SpecialMove> specialMoves) {
		// TODO Auto-generated method stub
		this.specialMoves = specialMoves;
	}

	@Override
	public String toString() {
		return "Pokemon [pokemonName=" + pokemonName + ", info=" + info + ", baseStat=" + baseStat + ", physique="
				+ physique + ", specialMoves=" + specialMoves + "]";
	}
}
