package com.teqlip.email;

public class NewAccountEmail extends NoReplyEmail{
	public static final String subject = "New Account Credentials";
	
	public NewAccountEmail(String name, String username, String password, String toEmailAddress) {
		super(subject, toEmailAddress, createBody(name,username, password));
		
	}
	
	private static String createBody(String name, String username, String password) {
		String dear = "Greetings " + name + ", \n";
		String body = "We are emailing to let you know that your account has been created. Please log in using the following credentials. \n";
		String credential1 = "Username: " + username + " \n";
		String credential2 = "Password: " + password + " \n";
		return dear + "\n" + body + "\n"+ credential1+ "\n" + credential2;
	}

}
