package com.eprostam.first.app.ws.security;

public class SecurityConstants {
	
	static public long EXPERATION_TIME = 36000; // experiation delai of the token : 1 hour
	static public String TOKEN_PREFIX = "Bearer "; // prefix of the token
	static public String HEADER_STRING = "Authorization";
	static public String SIGNUP_URL = "/users";
	static public String TOKEN_SECRET_WORD = "YES12345TESTINGSTRINGBOOT";

}
