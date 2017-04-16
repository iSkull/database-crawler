package com.pokemon.go.database.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pokemon.go.database.resource.ErrorCode;

public class ConnectionNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 4920148589861534045L;

	public ConnectionNotFoundException(String message) {
		super(Response.status(Status.NOT_FOUND)
				.entity(new ErrorItem(
						ErrorCode.CONNECTION_NOT_FOUND_EXCEPTION.getCode(),
						ErrorCode.CONNECTION_NOT_FOUND_EXCEPTION.name(), message))
				.type(MediaType.APPLICATION_JSON).build());
	}
}
