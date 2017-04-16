package com.pokemon.go.database.model;

import java.util.List;

import javax.persistence.Id;

public class Move {

	@Id
	private String id;
	private String moveName;
	private String moveType;
	private String type;
	private String power;
	private String energy;
	private String duration;
	private String damage;
	private String DPS;
	private String STABDPS;
	private String criticalChance;
	private String accuracy;
	private List<String> effective;
	private List<String> notEffective;
	private List<String> usedBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMoveName() {
		return moveName;
	}

	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getEnergy() {
		return energy;
	}

	public void setEnergy(String energy) {
		this.energy = energy;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public String getDPS() {
		return DPS;
	}

	public void setDPS(String dPS) {
		DPS = dPS;
	}

	public String getSTABDPS() {
		return STABDPS;
	}

	public void setSTABDPS(String sTABDPS) {
		STABDPS = sTABDPS;
	}

	public String getCriticalChance() {
		return criticalChance;
	}

	public void setCriticalChance(String criticalChance) {
		this.criticalChance = criticalChance;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public List<String> getEffective() {
		return effective;
	}

	public void setEffective(List<String> effective) {
		this.effective = effective;
	}

	public List<String> getNotEffective() {
		return notEffective;
	}

	public void setNotEffective(List<String> notEffective) {
		this.notEffective = notEffective;
	}

	public List<String> getUsedBy() {
		return usedBy;
	}

	public void setUsedBy(List<String> usedBy) {
		this.usedBy = usedBy;
	}
}
