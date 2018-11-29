package org.oos.domain;

import lombok.Data;

@Data
public class AuthDTO {

	private String access_token;
	private String type;
	private String refresh_token;
	private Integer expires_in;
	private String scope;
}
