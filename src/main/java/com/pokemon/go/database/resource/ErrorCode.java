package com.pokemon.go.database.resource;

public enum ErrorCode {
	INVALID_INPUT_PARAMETER(90001),
	INVALID_PASSWORD_EXCEPTION(90012),
	TABLE_NOT_FOUND_EXCEPTION(90100),
	CONNECTION_NOT_FOUND_EXCEPTION(90101);

	private int code;

	ErrorCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
