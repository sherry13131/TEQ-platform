package com.teqlip.email.type;

public class ChangedPassEmail extends NoReplyEmail {

	public static final String subject = "Password Change Notification";
	
	public ChangedPassEmail(String toEmailAddress) {
		this("", toEmailAddress);
	}
	
	public ChangedPassEmail(String username, String toEmailAddress) {
		super(subject, toEmailAddress, createBody(username));
	}

	private static String createBody(String name) {
		String dear = "Greetings " + name + ", \n";
		String body = "We are emailing to let you know you've successfully changed your password.";
		return dear + "\n" + body;
	}
}
