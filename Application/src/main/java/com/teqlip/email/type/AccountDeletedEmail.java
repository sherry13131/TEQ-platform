package com.teqlip.email.type;

public class AccountDeletedEmail extends NoReplyEmail {
  
  public static final String subject = "Account Deleted Notification";

  public AccountDeletedEmail(String username, String toEmailAddress) {
    super(subject, toEmailAddress, createBody(username));
  }
  
  private static String createBody(String name) {
    String dear = "Greetings " + name + ", \n";
    String body = "We are emailing to let you know your account has been deleted. You are no longer allow to login to the system.";
    return dear + "\n" + body;
  }

}
