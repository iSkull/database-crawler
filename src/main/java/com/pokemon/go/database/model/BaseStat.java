package com.pokemon.go.database.model;

public class BaseStat {

	private String baseAttackValue;
	private String baseDefenceValue;
	private String baseStaminaValue;
	private String baseCaptureRateValue;
	private String baseFleeRateValue;

	public String getBaseAttackValue() {
		return baseAttackValue;
	}

	public void setBaseAttackValue(String baseAttackValue) {
		this.baseAttackValue = baseAttackValue;
	}

	public String getBaseDefenceValue() {
		return baseDefenceValue;
	}

	public void setBaseDefenceValue(String baseDefenceValue) {
		this.baseDefenceValue = baseDefenceValue;
	}

	public String getBaseStaminaValue() {
		return baseStaminaValue;
	}

	public void setBaseStaminaValue(String baseStaminaValue) {
		this.baseStaminaValue = baseStaminaValue;
	}

	public String getBaseCaptureRateValue() {
		return baseCaptureRateValue;
	}

	public void setBaseCaptureRateValue(String baseCaptureRateValue) {
		this.baseCaptureRateValue = baseCaptureRateValue;
	}

	public String getBaseFleeRateValue() {
		return baseFleeRateValue;
	}

	public void setBaseFleeRateValue(String baseFleeRateValue) {
		this.baseFleeRateValue = baseFleeRateValue;
	}

	@Override
	public String toString() {
		return "BaseStat [baseAttackValue=" + baseAttackValue + ", baseDefenceValue=" + baseDefenceValue
				+ ", baseStaminaValue=" + baseStaminaValue + ", baseCaptureRateValue=" + baseCaptureRateValue
				+ ", baseFleeRateValue=" + baseFleeRateValue + "]";
	}

}
