package com.teqlip.email;

public class AccountCreatedEmail extends NoReplyEmail {

  public static final String subject = "Account Created Notification";
    
  public AccountCreatedEmail(String username, String toEmailAddress, String password) {
    super(subject, toEmailAddress, createBody(username, password));
  }

  private static String createBody(String name, String password) {
    String dear = "Greetings " + name + ", \n";
    String body = "We are emailing to let you know you've successfully created account.\nThe auto generated password is: " + password;
    return dear + "\n" + body;
  }

}
