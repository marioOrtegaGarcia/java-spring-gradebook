package com.mortegagarcia.gradebook.exceptions;

import javax.naming.AuthenticationException;

public class EmailNotFoundException extends AuthenticationException {
	public EmailNotFoundException(String s) {
		super(s);
	}
}
