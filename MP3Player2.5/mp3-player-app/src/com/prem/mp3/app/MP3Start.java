package com.prem.mp3.app;

public class MP3Start {

	/**
	 * 1. Separate the view code to the player running code. --- seems ok ,
	 * checking for any discrepency and suitable action will take place if found
	 * something wrong
	 * 
	 * 2. Save the playlist in xml format and try to encrypt it and run with the
	 * given saved file.
	 * 
	 * 3. make some attractive GUI
	 * 
	 * 4. highlight the currently playing song
	 * 
	 * 5. some keyEventListeners to display the songs of currently playing songs
	 * {similar functionality as that exists in notepad++}
	 */
	public static void main(String[] argv) {
		MP3Player objMP3Player = MP3Player.getInstance();
	}

}
