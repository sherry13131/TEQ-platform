package com.teqlip.gui.panels.utsc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;

@SuppressWarnings("serial")
public class UTSCDataPanel extends BodyPanel {
  
  public class ActionConsts {
    private static final String EXECUTE = "execute";
    private static final String SAVE = "save";
    private static final String BACK = "back";
    private static final String GETDATA = "get data";
  }
  
  private static final Dimension QUERY_DIMENSION = new Dimension(300, 30);
  
  private BoxLayout layout;
  @SuppressWarnings("rawtypes")
  private JComboBox chooseSavedTemplate;
  private JTextField templateField;
  
  public UTSCDataPanel(AppFrame main) {
    super(main);
    layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
    super.setLayout(layout);
    JComponent savedTemplatePane = null;
    try {
      savedTemplatePane = createSavedTemplatePane();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    JComponent templatePane = createTemplatePane();
    JComponent buttonPane = createButtonPane();
    
    add(savedTemplatePane);
    add(templatePane);
    add(buttonPane);
  }

  public JComponent createSavedTemplatePane() throws SQLException {
    JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
    
    JLabel savedQueryLabel = new JLabel("Saved Templates:");
    //TODO need a new function to save savedTemplateslist to database 
    List<String> savedTemplatesList = new ArrayList<String>();
    String [] savedTemplatesArray = new String[savedTemplatesList.size()];
    for (int i = 0; i < savedTemplatesList.size(); i++) {
        savedTemplatesArray[i] = savedTemplatesList.get(i);
    }
    this.chooseSavedTemplate = JGuiHelper.createComboBox(savedTemplatesArray, this, ActionConsts.GETDATA);
    this.chooseSavedTemplate.setPreferredSize(QUERY_DIMENSION);
    p.add(savedQueryLabel);
    p.add(this.chooseSavedTemplate);
    
    return p;
  }
  
  public JComponent createTemplatePane() {
    JPanel p = JGuiHelper.createPanelFlow();

    JLabel templateLabel = new JLabel("Template:");
    this.templateField = JGuiHelper.createTextField();
    this.templateField.setMaximumSize(new Dimension(400, 25));
    JButton executeBtn = JGuiHelper.createButton("Execute Template", this, ActionConsts.EXECUTE);
    JButton saveBtn = JGuiHelper.createButton("Save Template", this, ActionConsts.SAVE);
    p.add(templateLabel);
    p.add(this.templateField);
    p.add(executeBtn);
    p.add(saveBtn);
    return p;
  }
  
  public static DefaultTableModel buildTableModel(ResultSet rs)
      throws SQLException {

      ResultSetMetaData metaData = rs.getMetaData();

      // names of columns
      Vector<String> columnNames = new Vector<String>();
      int columnCount = metaData.getColumnCount();
      for (int column = 1; column <= columnCount; column++) {
          columnNames.add(metaData.getColumnName(column));
      }

      // data of the table
      Vector<Vector<Object>> data = new Vector<Vector<Object>>();
      while (rs.next()) {
          Vector<Object> vector = new Vector<Object>();
          for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
              vector.add(rs.getObject(columnIndex));
          }
          data.add(vector);
      }

      return new DefaultTableModel(data, columnNames);

  }
  
  public JComponent createButtonPane() {
    JPanel p = JGuiHelper.createPanelFlow();
    
    JButton backBtn = JGuiHelper.createButton("Back", this, ActionConsts.BACK);
    
    p.add(backBtn);
    
    return p;
  }
  
  @Override
  public void actionPerformed(ActionEvent arg0) {
    String cmd = arg0.getActionCommand();
    
    if (cmd.equals(ActionConsts.BACK)) {
      super.goToMenu(MenuOptions.UTSC_MAIN_MENU);
    }
  }

}
