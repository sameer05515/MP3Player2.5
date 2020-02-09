package com.prem.mp3.app.amend.comp;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class FileExplorerTable extends FileExplorerBaseTable {

	public FileExplorerTable() {
		init();
	}

	private void init() {
		// --- part 1
		// setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
		// { null ,null}, { null ,null}, { null ,null}, { null ,null}, { null
		// ,null}, { null ,null},
		// { null ,null}, { null ,null}, { null ,null}, { null ,null} },
		// new String[] {"Track No", "Files" }));
		setModel(new MyTableModel());
//		getColumnModel().getColumn(0).setPreferredWidth(50);
//
//		// ---- part 2
//		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		getTableHeader().setReorderingAllowed(false);
//		getTableHeader().setResizingAllowed(true);
//		setFillsViewportHeight(true);
	}

}
