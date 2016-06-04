package com.rootser.bluerootser.constants;
/**
 * 
 * @author john
 * This class arouse out of the desire to have the compiler check
 * that we are using the right values in the web security configuration class
 * and the controllers, and the web mvc configuration.
 * 
 * Annotations will only accept constant values, so we cannot use an enum.
 */
public class Constants {
	public static final String contextName="bluerootser";
	public static final String articles = "articles";
	public static final String home = "home";
	public static final String resources = "resources/**";
	public static final String root = "/";
	public static final String help = "help";
	public static final String memorizationTechniques = "memorizationTechniques";
	public static final String myArea = "myArea";
	public static final String signup = "signup";
	public static final String createUser = "createUser";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ERROR = "error";
}
