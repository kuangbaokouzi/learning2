package com.entor.bms.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Authentication extends Authenticator {
	private String username;
	private String password;

	public Authentication() {
	}

	public Authentication(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication pa = new PasswordAuthentication(username, password);
		return pa;
	}
}
