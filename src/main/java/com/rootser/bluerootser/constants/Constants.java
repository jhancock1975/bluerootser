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
	public static final String articles = "/articles";
	public static final String home = "/home";
	public static final String resources = "/resources/**";
	public static final String root = "/";
	public static final String help = "/help";
}
