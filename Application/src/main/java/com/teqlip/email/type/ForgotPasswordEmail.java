package com.teqlip.email.type;

public class ForgotPasswordEmail extends NoReplyEmail {

  public static final String subject = "Forgot password Notification";
  
  public ForgotPasswordEmail(String username, String toEmailAddress, String password) {
    super(subject, toEmailAddress, createBody(username, password));
  }
  
  private static String createBody(String name, String password) {
    String dear = "Greetings " + name + ", \n";
    String body = "We are emailing to let you know your new password.\nThe auto generated password is: " + password;
    return dear + "\n" + body;
  }

}
