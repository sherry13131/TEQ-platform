package com.teqlip.email;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;

public abstract class EmailAbstract implements EmailInterface {

	private Email from;
	private String subject;
	private Email to;
	private String style = "text/plain";
	private Content content;
	
	@Override
	public Mail getMail() {
		return new Mail(from, subject, to, content);
	}
	
	public void setFromEmail(String address) {
		this.from = new Email(address);
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setToEmail(String address) {
		this.to = new Email(address);
	}
	
	public void setContent(String content) {
		this.content = new Content(style, content); 
	}
}
