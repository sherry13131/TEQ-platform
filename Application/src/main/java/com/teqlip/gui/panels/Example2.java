package com.teqlip.gui.panels;

import java.io.IOException;
import com.sendgrid.*;

public class Example2 {
	
	public static final String TEST_API_KEY = "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";
	
	public static void main(String[] args) throws IOException {
	    Email from = new Email("anthony.leung.canada@gmail.com");
	    String subject = "Sending with SendGrid is Fun";
	    Email to = new Email("anthony.leung.canada@gmail.com");
	    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(TEST_API_KEY);
	    Request request = new Request();
	    
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	  }
}
