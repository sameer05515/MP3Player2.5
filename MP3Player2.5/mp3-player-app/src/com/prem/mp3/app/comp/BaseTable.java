/*
 * CTable.java
 *
 * Created on February 4, 2005, 11:49 AM by AKASH GROVER
 */

package com.prem.mp3.app.comp;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author cipa
 */
public class BaseTable extends JTable {
	Vector rowListeners = new Vector();
	Vector rowSingleClickedListener = new Vector();

	public BaseTable() {
		HashSet set = new HashSet();
		set.add(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_TAB, 0));
		setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, set);

		set = new HashSet();
		set.add(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_TAB, KeyEvent.SHIFT_MASK));
		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, set);

		AbstractAction insertAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				insertIntoTable();
			}
		};

		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0),
				"insert");
		getActionMap().put("insert", insertAction);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int row = BaseTable.this.getSelectedRow();
					fireRowDoubleClickedEvent(row);
				}
				if (event.getClickCount() == 1) {
					int row = BaseTable.this.getSelectedRow();
					fireRowSingleClickedEvent(row);
				}
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F2) {
					e.consume();
					return;
				}

				if (e.getKeyCode() == KeyEvent.VK_INSERT) {
					insertIntoTable();
					e.setKeyCode(KeyEvent.VK_F2);
				}
			}
		});
		// addFocusListener(new FocusAdapter(){
		// public void focusLost(FocusEvent e){
		// int row=getEditingRow();
		// int col=getEditingColumn();
		// if(row<0 || col<0){
		// return;
		// }
		// CellEditor ed=getCellEditor(row, col);
		// if(ed!=null){
		// ed.stopCellEditing();
		// }
		// }
		// });
	}

	public boolean validateCTable(Vector errList, int col, String msg) {
		int rowCont = getRowCount();
		for (int i = 0; i < rowCont; i++) {
			String value = (String) getValueAt(i, col);
			for (int j = 0; j < rowCont; j++) {
				if (j == i)
					continue;
				String value1 = (String) getValueAt(j, col);
				// if(value1==null){
				// return true;
				// }
				if ((value1 != null && value != null) && !value.equals("")
						&& value.equals(value1)) {
					errList.add(ErrorMessages.getErrorCodeDesc(msg));
					return false;
				}
			}
		}
		return true;
	}

	public void addCTableRowListener(CTableRowListener listener) {
		rowListeners.add(listener);
	}

	public void addCTableRowListener(CTableRowSingleClickedListener listener) {
		rowSingleClickedListener.add(listener);
	}

	/** returns the total number of fillerd rows in the table */
	public int getNumFilledRows() {
		int row = -1;
		while (true) {
			if (isRowEmpty(++row))
				return row; // End of Data in Table; Return
		}
	}

	/** returns true if row with given index is empty */
	public boolean isRowEmpty(int row) {
		if (row >= getRowCount())
			return true;
		for (int col = 0, columnCount = getColumnCount(); col < columnCount; col++) {
			String theStr = (String) getValueAt(row, col);
			if (theStr != null)
				theStr = theStr.trim();
			if (theStr != null && theStr.length() > 0)
				return false;
		}

		return true;
	}

	/** appends a new row in the table at the bottom */
	public void appendNewRow() {
		Object[] obj = {};
		((DefaultTableModel) this.getModel()).addRow(obj);
	}

	/**
	 * disables cell editing in the table directly. ...rows can still be
	 * selected
	 */
	public void setTableDisabled() {
		JTextField tf = new JTextField();
		tf.setEditable(false);
		int cols = getColumnCount();
		for (int i = 0; i < cols; i++)
			getColumnModel().getColumn(i).setCellEditor(
					new DefaultCellEditor(tf));

	}

	private void insertIntoTable() {
		int row = getSelectedRow();
		int col = getSelectedColumn();
		if (row < 0 || col < 0) {
			return;
		}
		fireRowDoubleClickedEvent(row);

	}

	protected void fireRowDoubleClickedEvent(int row) {
		// ////////call listeners
		if (!isEnabled()) {// if disabled then ignore
			return;
		}

		Iterator iter = rowListeners.iterator();

		if (rowListeners.size() != 0) {
			CellEditor editor = getCellEditor();
			if (editor != null) {
				editor.stopCellEditing();
			}
		}

		while (iter.hasNext()) {
			((CTableRowListener) iter.next()).rowDoubleClicked(row);
		}
		// ///called all liseners
	}

	protected void fireRowSingleClickedEvent(int row) {
		// ////////call listeners
		if (!isEnabled()) {// if disabled then ignore
			return;
		}

		Iterator iter = rowSingleClickedListener.iterator();

		if (rowSingleClickedListener.size() != 0) {
			CellEditor editor = getCellEditor();
			if (editor != null) {
				editor.stopCellEditing();
			}
		}

		while (iter.hasNext()) {
			((CTableRowSingleClickedListener) iter.next())
					.rowSingleClicked(row);
		}
		// ///called all liseners
	}

}
