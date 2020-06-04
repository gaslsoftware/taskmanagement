package com.gasl.taskmanagement.vo;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8777507613919608112L;

	private final String accessToken;

	public AuthenticationResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getJwt() {
		return accessToken;
	}
}