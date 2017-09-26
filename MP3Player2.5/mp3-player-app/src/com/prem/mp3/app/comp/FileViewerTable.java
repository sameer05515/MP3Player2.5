/*###########################################################
 *CLASS: FileViewerTable
 *PURPOSE :
 *REMARKS:
 *LAST MODIFIED
 *Premendra Kumar 
 *###############################################################*/
/*
 * CaseDiaryTable.java
 *
 * Created on February 7, 2005, 4:14 PM
 */

package com.prem.mp3.app.comp;

import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * 
 * @author cipa
 */
public class FileViewerTable extends BaseTable {

	List<String> vecEntries = null;

	/** Creates a new instance of SubmittedByTable */
	public FileViewerTable() {
		initSubmittedByTable();
		init();
	}

	protected void init() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(true);
		setTableDisabled();
	}

	private void initSubmittedByTable() {
		setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
				{ null ,null}, { null ,null}, { null ,null}, { null ,null}, { null ,null}, { null ,null},
				{ null ,null}, { null ,null}, { null ,null}, { null ,null} },
				new String[] {"Track No", "Files" }));
		getColumnModel().getColumn(0).setPreferredWidth(50);
//		getColumnModel().getColumn(1).setPreferredWidth(150);
//		getColumnModel().getColumn(2).setPreferredWidth(580);
		setTableDisabled();
	}

	public void setCaseDiaryVector(List<String> vecEntries) {
		this.vecEntries = vecEntries;
		int noOfRows = this.getModel().getRowCount();

		for (int i = 0, size = vecEntries.size(); i < size; i++) {
			if (noOfRows == i + 1) {
				this.appendNewRow();
				noOfRows++;
			}
			this.setValueAt((i+1)+"", i, 0);
			this.setValueAt(vecEntries.get(i), i, 1);		
		}
	}

	public List<String> getCaseDiaryVector() {
		return vecEntries;
	}

	
}