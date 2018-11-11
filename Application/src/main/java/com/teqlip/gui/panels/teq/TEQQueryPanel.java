package com.teqlip.gui.panels.teq;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

import com.teqlip.gui.frames.AppFrame;
import com.teqlip.gui.helper.JGuiHelper;
import com.teqlip.gui.panels.BodyPanel;
import com.teqlip.gui.panels.BodyPanel.MenuOptions;
import com.teqlip.database.*;

@SuppressWarnings("serial")
public class TEQQueryPanel extends BodyPanel {

	public class ActionConsts {
		private static final String EXECUTE = "execute";
		private static final String SAVE = "save";
		private static final String BACK = "back";
	}

	private static final Dimension QUERY_DIMENSION = new Dimension(300, 30);

	private BoxLayout layout;
	private JComboBox chooseSavedQuery;
	private JTextField queryField;

    public TEQQueryPanel(AppFrame main) {
        super(main);
        
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(layout);
        
        JComponent savedQueryPane = createSavedQueryPane();
        JComponent queryPane = createQueryPane();
        JComponent buttonPane = createButtonPane();
        
        add(savedQueryPane);
        add(queryPane);
        add(buttonPane);
    }

    // TODO get saved queries from db

    public JComponent createSavedQueryPane() {
        JPanel p = JGuiHelper.createPanelBox(BoxLayout.PAGE_AXIS);
        
        JLabel savedQueryLabel = new JLabel("Saved Queries:");
    	// this.chooseSavedQuery = JGuiHelper.createComboBox(
    	// this.chooseSavedQuery.setPreferredSize(QUERY_DIMENSION);
    	
    	p.add(savedQueryLabel);
    	// p.add(this.chooseSavedQuery);
    	
    	return p;
    }

    public JComponent createQueryPane() {
        JPanel p = JGuiHelper.createPanelFlow();

        JLabel queryLabel = new JLabel("Query:");
        this.queryField = JGuiHelper.createTextField();
        this.queryField.setMaximumSize(new Dimension(400, 25));
        JButton executeBtn = JGuiHelper.createButton("Execute Query", this, ActionConsts.EXECUTE);
        JButton saveBtn = JGuiHelper.createButton("Save Query", this, ActionConsts.SAVE);
        p.add(queryLabel);
        p.add(this.queryField);
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
    public void actionPerformed(ActionEvent ae) {
    	String cmd = ae.getActionCommand();

        if (cmd.equals(ActionConsts.EXECUTE)) {
            try {
                Connection con = DatabaseDriverHelper.connectDataBase();
                ResultSet rs = DatabaseSelector.queryData(con, this.queryField.getText());
                JTable table = new JTable(buildTableModel(rs));
                con.close();
                JOptionPane.showMessageDialog(null, new JScrollPane(table), "Data Table Preview", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (cmd.equals(ActionConsts.SAVE)) {
        	// TODO Save query into db
        } else if (cmd.equals(ActionConsts.BACK)) {
        	super.goToMenu(MenuOptions.MAIN_MENU);
        }
    }
}
