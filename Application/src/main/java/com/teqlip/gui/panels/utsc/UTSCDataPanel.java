package com.teqlip.gui.panels.utsc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Connection;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.teqlip.Role.RoleEnum;
import com.teqlip.database.DatabaseDriverHelper;
import com.teqlip.database.DatabaseSelector;
import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;
import com.teqlip.gui.panels.teq.TEQQueryPanel.ActionConsts;

import Templates.TemplateEnum;

@SuppressWarnings("serial")
public class UTSCDataPanel extends BodyPanel {
  
  public class ActionConsts {
    private static final String EXECUTE = "execute";
    private static final String BACK = "back";
    private static final String GETDATA = "get data";
  }
  
  private static final Dimension QUERY_DIMENSION = new Dimension(300, 30);
  
  private BoxLayout layout;
  @SuppressWarnings("rawtypes")
  private JComboBox chooseTemplate;
  
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
    
    JLabel savedQueryLabel = new JLabel("Templates:");
    //get the all templates name (table names) from enum
    String [] templateNames = new String[TemplateEnum.values().length];
    int i = 0;
    for (TemplateEnum template : TemplateEnum.values()) {
    	templateNames[i] = template.getString();
    	i++;
    }
    this.chooseTemplate = JGuiHelper.createComboBox(templateNames, this, ActionConsts.GETDATA);
    this.chooseTemplate.setPreferredSize(QUERY_DIMENSION);
    p.add(chooseTemplate);
    p.add(this.chooseTemplate);
    
    return p;
  }
  
  public JComponent createTemplatePane() {
    JPanel p = JGuiHelper.createPanelFlow();
    JButton executeBtn = JGuiHelper.createButton("Get Data", this, ActionConsts.EXECUTE);
    p.add(executeBtn);
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
    } else if (cmd.equals(ActionConsts.EXECUTE)) {
        try {
            Connection con = DatabaseDriverHelper.connectDataBase();
            ResultSet rs = DatabaseSelector.getAnonymizedData(con, (String)this.chooseTemplate.getSelectedItem());
            JTable table = new JTable(buildTableModel(rs));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            con.close();
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Data For "+this.chooseTemplate.getSelectedItem() + " Preview", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  }

}
