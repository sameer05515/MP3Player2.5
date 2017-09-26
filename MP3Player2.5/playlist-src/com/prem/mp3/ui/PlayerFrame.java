package com.prem.mp3.ui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class PlayerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * @throws HeadlessException
	 */
	public PlayerFrame() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gc
	 */
	public PlayerFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @param gc
	 */
	public PlayerFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public PlayerFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

}
