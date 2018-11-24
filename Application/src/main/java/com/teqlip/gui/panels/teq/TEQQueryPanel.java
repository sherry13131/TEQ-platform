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
import com.teqlip.exceptions.*;

@SuppressWarnings("serial")
public class TEQQueryPanel extends BodyPanel {

	public class ActionConsts {
		private static final String EXECUTE = "execute";
		private static final String SAVE = "save";
		private static final String BACK = "back";
        private static final String GETQUERY = "get query";
        private static final String EXPORT = "export csv";
        private static final String DELETE = "delete";
	}

	private static final Dimension QUERY_DIMENSION = new Dimension(300, 30);

	private BoxLayout layout;
	private JComboBox chooseSavedQuery;
	private JTextField queryField;

    public TEQQueryPanel(AppFrame main) {
        super(main);
        
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        super.setLayout(layout);
        JComponent savedQueryPane = null;
        try {
            savedQueryPane = createSavedQueryPane();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JComponent queryPane = createQueryPane();
        JComponent buttonPane = createButtonPane();
        
        add(savedQueryPane);
        add(queryPane);
        add(buttonPane);
    }

    public JComponent createSavedQueryPane() throws SQLException {
        JPanel p = JGuiHelper.createPanelFlow();
        
        JLabel savedQueryLabel = new JLabel("Saved Queries:");
        List<String> savedQueriesList = DatabaseSelectHelper.getSavedQueries();
        String [] savedQueriesArray = new String[savedQueriesList.size()];
        for (int i = 0; i < savedQueriesList.size(); i++) {
            savedQueriesArray[i] = savedQueriesList.get(i);
        }
    	this.chooseSavedQuery = JGuiHelper.createComboBox(savedQueriesArray, this, ActionConsts.GETQUERY);
    	this.chooseSavedQuery.setPreferredSize(QUERY_DIMENSION);
        JButton deleteBtn = JGuiHelper.createButton("Delete Saved Query", this, ActionConsts.DELETE);
    	p.add(savedQueryLabel);
    	p.add(this.chooseSavedQuery);
        p.add(deleteBtn);
    	
    	return p;
    }

    public JComponent createQueryPane() {
        JPanel p = JGuiHelper.createPanelFlow();

        JLabel queryLabel = new JLabel("Query:");
        this.queryField = JGuiHelper.createTextField();
        this.queryField.setMaximumSize(new Dimension(400, 25));
        JButton executeBtn = JGuiHelper.createButton("Execute Query", this, ActionConsts.EXECUTE);
        JButton saveBtn = JGuiHelper.createButton("Save Query", this, ActionConsts.SAVE);
        JButton expBtn = JGuiHelper.createButton("Export CSV", this, ActionConsts.EXPORT);
        p.add(queryLabel);
        p.add(this.queryField);
        p.add(executeBtn);
        p.add(saveBtn);
        p.add(expBtn);
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
                JOptionPane.showMessageDialog(null, "There is something wrong in the query.", "Wrong query input", JOptionPane.ERROR_MESSAGE);
            }
        } else if (cmd.equals(ActionConsts.SAVE)) {
        	Connection con = DatabaseDriverHelper.connectDataBase();
            String query = this.queryField.getText();
            try {
                DatabaseInsertHelper.insertNewQuery(query, con);
                con.close();
            } catch (DatabaseInsertException e) {
                JOptionPane.showMessageDialog(null, "Saved query already exists", "Error", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (cmd.equals(ActionConsts.BACK)) {
        	super.goToMenu(MenuOptions.MAIN_MENU);
        } else if (cmd.equals(ActionConsts.GETQUERY)) {
            this.queryField.setText((String)this.chooseSavedQuery.getSelectedItem());
        } else if (cmd.equals(ActionConsts.EXPORT)) {
            ExportData.exportCSV(this.queryField.getText());
        } else if (cmd.equals(ActionConsts.DELETE)) {
            try {
                int id = DatabaseSelectHelper.getSavedQueryID((String)this.chooseSavedQuery.getSelectedItem());
                DatabaseDeleteHelper.deleteQueryHelper(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
