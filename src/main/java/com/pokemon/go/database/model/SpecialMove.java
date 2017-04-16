package com.pokemon.go.database.model;

import java.util.List;

public class SpecialMove {

	private String SpecialMoveName;
	private String specialMoveMoveType;
	private List<String> specialMoveType;
	private String DPSValue;
	private String accuracyValue;
	private String criticalChanceValue;
	private String energyValue;
	private String STABbonusValue;
	private String STABDPSValue;

	public String getSpecialMoveName() {
		return SpecialMoveName;
	}

	public void setSpecialMoveName(String specialMoveName) {
		SpecialMoveName = specialMoveName;
	}

	public String getSpecialMoveMoveType() {
		return specialMoveMoveType;
	}

	public void setSpecialMoveMoveType(String specialMoveMoveType) {
		this.specialMoveMoveType = specialMoveMoveType;
	}

	public List<String> getSpecialMoveType() {
		return specialMoveType;
	}

	public void setSpecialMoveType(List<String> specialMoveType) {
		this.specialMoveType = specialMoveType;
	}

	public String getDPSValue() {
		return DPSValue;
	}

	public void setDPSValue(String dPSValue) {
		this.DPSValue = dPSValue;
	}

	public String getAccuracyValue() {
		return accuracyValue;
	}

	public void setAccuracyValue(String accuracyValue) {
		this.accuracyValue = accuracyValue;
	}

	public String getCriticalChanceValue() {
		return criticalChanceValue;
	}

	public void setCriticalChanceValue(String criticalChanceValue) {
		this.criticalChanceValue = criticalChanceValue;
	}

	public String getEnergyValue() {
		return energyValue;
	}

	public void setEnergyValue(String energyValue) {
		this.energyValue = energyValue;
	}

	public String getSTABBonusValue() {
		return STABbonusValue;
	}

	public void setSTABBonusValue(String STABbonusValue) {
		this.STABbonusValue = STABbonusValue;
	}

	public String getSTABDPSValue() {
		return STABDPSValue;
	}

	public void setSTABDPSValue(String STABDPSValue) {
		this.STABDPSValue = STABDPSValue;
	}

	@Override
	public String toString() {
		return "SpecialMove [SpecialMoveName=" + SpecialMoveName + ", specialMoveMoveType=" + specialMoveMoveType
				+ ", specialMoveType=" + specialMoveType + ", DPSValue=" + DPSValue + ", accuracyValue=" + accuracyValue
				+ ", criticalChanceValue=" + criticalChanceValue + ", energyValue=" + energyValue + ", STABbonusValue="
				+ STABbonusValue + ", STABDPSValue=" + STABDPSValue + "]";
	}
}
