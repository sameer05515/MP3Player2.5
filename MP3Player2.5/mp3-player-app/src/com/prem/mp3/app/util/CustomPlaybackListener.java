package com.prem.mp3.app.util;

//import com.prem.mp3.app.util.PlayerUtility.Interruption;

import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import com.prem.mp3.app.MP3Player;
import com.prem.mp3.app.util.PlayerUtility.Interruption;

public class CustomPlaybackListener extends PlaybackListener {
	public PlayerUtility objPlayerUtility = null;

	//private MP3Player mp3Player = null;

	public CustomPlaybackListener(PlayerUtility objPlayerUtility) {
		this.objPlayerUtility = objPlayerUtility;
	}

	public void playbackStarted(PlaybackEvent playbackEvent) {
		// System.out.println("playbackStarted()");
	}

	public void playbackFinished(PlaybackEvent playbackEvent) {
		 System.out.println("playbackEnded()");
		objPlayerUtility.setCurrentPlayFinished(true);
		
		System.out.println(objPlayerUtility.getCurrentInterruption().name());
		if (objPlayerUtility.getFilePaths() != null
				&& objPlayerUtility.getFilePaths().length > 0) {
			if (objPlayerUtility.getCurrentInterruption() != Interruption.STOP) {
				
				if (objPlayerUtility.getCurrentInterruption() == Interruption.CONTINUE) {
					objPlayerUtility
							.setCounter(((objPlayerUtility.getCounter() + 1) % (objPlayerUtility
									.getFilePaths().length)));
					objPlayerUtility.play();
//					if (mp3Player != null) {
//						mp3Player.refreshTable();
//					}
				} else if (objPlayerUtility.getCurrentInterruption() == Interruption.NEXT) {
					objPlayerUtility
							.setCounter(((objPlayerUtility.getCounter() + 1) % (objPlayerUtility
									.getFilePaths().length)));
					objPlayerUtility.play();
//					if (mp3Player != null) {
//						mp3Player.refreshTable();
//					}
				} else if (objPlayerUtility.getCurrentInterruption() == Interruption.PREVIOUS) {
					objPlayerUtility
							.setCounter(((objPlayerUtility.getCounter() - 1 + objPlayerUtility
									.getFilePaths().length) % (objPlayerUtility
									.getFilePaths().length)));
					
					objPlayerUtility.play();
//					if (mp3Player != null) {
//						mp3Player.refreshTable();
//					}
				} else if (objPlayerUtility.getCurrentInterruption() == Interruption.BROWSE_NEW) {
					
					objPlayerUtility.setCounter(0);
					objPlayerUtility.play();
					//System.out.println("mp3Player == "+mp3Player);
//					if (mp3Player != null) {
//						mp3Player.refreshTable();
//					}
				} else if (objPlayerUtility.getCurrentInterruption() == Interruption.BROWSE_ADD) {
					/**
					 * Do nothing user has right to add new songs in his
					 * playlist indeed
					 */
					objPlayerUtility.play();
					//System.out.println("mp3Player == "+mp3Player);
//					if (mp3Player != null) {
//						mp3Player.refreshTable();
//					}
				}else if(objPlayerUtility.getCurrentInterruption() == Interruption.JUMP){
					objPlayerUtility.play();
					//System.out.println("mp3Player == "+mp3Player);
//					if (mp3Player != null) {
//						mp3Player.refreshTable();
//					}
				}
			} else {
				System.out.println("Forced to stop!!");
			}
			//------------------essetial -----------------------
			
			if (getMp3Player() != null) {
				getMp3Player().refreshTable();
			}
		}

	}

	// Runnable members

	// public void run() {
	// try {
	// objPlayerUtility.getPlayer().play();
	// } catch (javazoom.jl.decoder.JavaLayerException ex) {
	// ex.printStackTrace();
	// }
	//
	// }

	/**
	 * @return the objPlayerUtility
	 */
	public PlayerUtility getObjPlayerUtility() {
		return objPlayerUtility;
	}

	/**
	 * @param objPlayerUtility
	 *            the objPlayerUtility to set
	 */
	public void setObjPlayerUtility(PlayerUtility objPlayerUtility) {
		this.objPlayerUtility = objPlayerUtility;
	}

	/**
	 * @return the mp3Player
	 */
	public MP3Player getMp3Player() {
		return MP3Player.getInstance();
	}

	/**
	 * @param mp3Player
	 *            the mp3Player to set
	 */
//	public void setMp3Player(MP3Player mp3Player) {
//		this.mp3Player = mp3Player;
//	}

}
