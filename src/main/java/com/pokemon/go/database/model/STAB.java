package com.pokemon.go.database.model;

public class STAB {
	private String bonusValue;
	private String DPSValue;

	public String getBonusValue() {
		return bonusValue;
	}

	public void setBonusValue(String bonusValue) {
		this.bonusValue = bonusValue;
	}

	public String getDPSValue() {
		return DPSValue;
	}

	public void setDPSValue(String dPSValue) {
		DPSValue = dPSValue;
	}

	@Override
	public String toString() {
		return "STAB [bonusValue=" + bonusValue + ", DPSValue=" + DPSValue + "]";
	}
}
