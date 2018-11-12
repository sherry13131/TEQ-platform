package com.teqlip.api;

import java.io.IOException;

import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class SendGridAPI {

	private static final String SENDGRID_API_KEY = "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";
	
	public static void sendEmail(Mail mail) {
		Logger.header("Sending email through SendGrid API...");
		
		SendGrid sg = new SendGrid(SENDGRID_API_KEY);
		
		Request request = initiateRequest();
		Response response;
		
		try {
			request.setBody(mail.build());
			response = sg.api(request);
			
			Logger.info("Email Successfully sent!");
			Logger.info("Email Status Code: " + response.getStatusCode());
			Logger.info("Email Body: " + response.getBody());
			Logger.info("Email Headers: " + response.getHeaders());
		} catch (IOException ex) {
			Logger.error("EMAIL HAS FAILED TO SEND");
		} 
	}
	
	
	private static Request initiateRequest() {
		Logger.info("Initiating request...");
	    Request request = new Request();
	    request.setMethod(Method.POST);
	    request.setEndpoint("mail/send");
	    return request;
	}
}
