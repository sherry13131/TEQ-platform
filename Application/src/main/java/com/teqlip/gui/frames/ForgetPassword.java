package com.teqlip.gui.frames;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.teqlip.database.DatabaseSelectHelper;
import com.teqlip.gui.helper.JGuiHelper;

@SuppressWarnings("serial")
public class ForgetPassword extends BaseFrame {

  private final static String SUBMIT = "submit";
  private final static String CANCEL = "cancel";

  public static final String[] INFO_FIELDS = {
      "Username",
      "Email"
  };
  
  public JTextField[] textFields = new JTextField[ForgetPassword.INFO_FIELDS.length];
  
  private BoxLayout layout;
  private Container container;

  public ForgetPassword() {
    super("Forget your password?");
    
    new DatabaseSelectHelper();
    container = getContentPane();
    layout = new BoxLayout(container, BoxLayout.PAGE_AXIS);
    setLayout(layout);
    
    JLabel msgs = createMessagePane();
    JComponent fields = createFieldsPane();
    JComponent buttonPane = createButtonPane();
    
    add(msgs);
    add(fields);
    add(buttonPane);
  }

  private JLabel createMessagePane() {
    String msg = "Enter your username and the email, "
        + "a new password will be sent to your email.";
    JLabel msglbl = new JLabel(msg);
    Font msgFont = msglbl.getFont();
    Font newFont = new Font(msgFont.getName(), msgFont.getStyle(), 15);
    msglbl.setFont(newFont);
    return msglbl;
  }

  private JComponent createFieldsPane() {
    JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    
      for (int i = 0; i < ForgetPassword.INFO_FIELDS.length; i++) {
          JLabel label = new JLabel(ForgetPassword.INFO_FIELDS[i]);
          this.textFields[i] = new JTextField();
          p.add(label);
          p.add(this.textFields[i]);
      }

    return p;
  }
  
  private JComponent createButtonPane() {
    JPanel p = new JPanel();
    JButton submitBtn = JGuiHelper.createButton("Submit", this, SUBMIT);
    JButton cancelBtn = JGuiHelper.createButton("Cancel", this, CANCEL);

    p.add(submitBtn);
    p.add(cancelBtn);

    return p;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();

    if (CANCEL.equals(cmd)) {
      this.dispose();
      LoginMenu login = new LoginMenu();
      login.packAndShow();
    }
  }

}
