package com.teqlip.email;

import java.util.ArrayList;
import java.util.List;

import com.teqlip.api.SendGridAPI;

public class EmailHandler {
	
	private static List<EmailInterface> mailList = new ArrayList<EmailInterface>();

	public static void addEmail(EmailInterface email) {
		mailList.add(email);
	}
	
	
	public static void sendEmails() {
	    for (EmailInterface email : mailList) {
	    	SendGridAPI.sendEmail(email.getMail());
	    }
	    
	    mailList.clear();
	}
	
}