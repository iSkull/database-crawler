package com.pokemon.go.database.model;

public class Physique {

	private String heightValue;
	private String XLHeightValue;
	private String XSHeightValue;
	private String weightValue;
	private String XLWeightValue;
	private String XSWeightValue;

	public String getHeightValue() {
		return heightValue;
	}

	public void setHeightValue(String heightValue) {
		this.heightValue = heightValue;
	}

	public String getXLHeightValue() {
		return XLHeightValue;
	}

	public void setXLHeightValue(String xLHeightValue) {
		XLHeightValue = xLHeightValue;
	}

	public String getXSHeightValue() {
		return XSHeightValue;
	}

	public void setXSHeightValue(String xSHeightValue) {
		XSHeightValue = xSHeightValue;
	}

	public String getWeightValue() {
		return weightValue;
	}

	public void setWeightValue(String weightValue) {
		this.weightValue = weightValue;
	}

	public String getXLWeightValue() {
		return XLWeightValue;
	}

	public void setXLWeightValue(String xLWeightValue) {
		XLWeightValue = xLWeightValue;
	}

	public String getXSWeightValue() {
		return XSWeightValue;
	}

	public void setXSWeightValue(String xSWeightValue) {
		XSWeightValue = xSWeightValue;
	}

	@Override
	public String toString() {
		return "Physique [heightValue=" + heightValue + ", XLHeightValue=" + XLHeightValue + ", XSHeightValue="
				+ XSHeightValue + ", weightValue=" + weightValue + ", XLWeightValue=" + XLWeightValue
				+ ", XSWeightValue=" + XSWeightValue + "]";
	}
}
