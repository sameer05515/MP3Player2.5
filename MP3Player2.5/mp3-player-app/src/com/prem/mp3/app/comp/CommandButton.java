package com.prem.mp3.app.comp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

import com.prem.mp3.app.util.PlayerUtility;

public class CommandButton extends JButton implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6886139919696686986L;
	private Command command ;
	private PlayerUtility playerUtility;
	
	public enum Command{
		CONTINUE, STOP, NEXT, PREVIOUS, BROWSE_NEW, BROWSE_ADD;
	}

	public CommandButton() {
		// TODO Auto-generated constructor stub
	}

	public CommandButton(Icon icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public CommandButton(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public CommandButton(Action a) {
		super(a);
		// TODO Auto-generated constructor stub
	}

	public CommandButton(String text, Icon icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
	
	public CommandButton(String text, Icon icon,Command command,PlayerUtility objPlayerUtility) {
		super(text, icon);
		this.command=command;
		this.playerUtility=objPlayerUtility;
		
	}
	
	public CommandButton(String text,Command command,PlayerUtility objPlayerUtility) {
		super(text);
		this.command=command;
		this.playerUtility=objPlayerUtility;
		this.setSize(VERTICAL, HEIGHT);
		this.setFocusable(false);
		this.addActionListener(this);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if(playerUtility==null){
			return;
		}		
		if (command==Command.STOP) {
			playerUtility.stop();
			
		} else if (command==Command.NEXT) {
			playerUtility.next();
		} else if (command==Command.PREVIOUS) {
			playerUtility.previous();
		} else if (command==Command.BROWSE_NEW) {
			playerUtility.newPlaylist();
		} else if (command==Command.BROWSE_ADD) {
			playerUtility.addMoreSongs();
		} else {
			System.out.println("Invalid command!");
		}

	}

	/**
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * @return the playerUtility
	 */
	public PlayerUtility getPlayerUtility() {
		return playerUtility;
	}

}
