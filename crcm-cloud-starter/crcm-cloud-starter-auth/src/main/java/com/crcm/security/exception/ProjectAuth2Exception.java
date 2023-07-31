
package com.crcm.security.exception;

import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

public class ProjectAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public ProjectAuth2Exception(String msg) {
		super(msg);
	}

	public ProjectAuth2Exception(String msg, int errorCode) {
		super(msg);
		this.errorCode = String.valueOf(errorCode);
	}
}
