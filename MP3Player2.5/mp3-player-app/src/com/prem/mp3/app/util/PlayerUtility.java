package com.prem.mp3.app.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

public class PlayerUtility /* extends PlaybackListener */implements Runnable {
	// private String filePath;
	private int counter = 0;
	private boolean currentPlayFinished = false;
	// private boolean autoIncrement = true;
	private String[] filePaths;
	private AdvancedPlayer player;
	private Thread playerThread;

	private Interruption currentInterruption = Interruption.CONTINUE;

	public enum Interruption {
		CONTINUE, STOP, NEXT, PREVIOUS, BROWSE_NEW, BROWSE_ADD, JUMP;
	}

	private enum Command {

	}

	/**
	 * @return the currentInterruption
	 */
	public Interruption getCurrentInterruption() {
		return currentInterruption;
	}

	/**
	 * @param currentInterruption
	 *            the currentInterruption to set
	 */
	public void setCurrentInterruption(Interruption currentInterruption) {
		this.currentInterruption = currentInterruption;
	}

	public PlayerUtility() {
		// this(new String[] { filePath });
		// this.filePath = filePath;
		customPlaybackListener = new CustomPlaybackListener(this);
	}

	public PlayerUtility(String filePath) {
		this(new String[] { filePath });
		// this.filePath = filePath;
	}

	public PlayerUtility(String[] filePaths) {
		if (filePaths != null && filePaths.length > 0) {
			this.filePaths = filePaths;
		} else {
			String[] fs = getBrowsedFiles();
			if (fs != null) {
				setFilePaths(fs);
			}
		}
	}

	JFileChooser fileChooser = new JFileChooser();

	public String[] getBrowsedFiles() {
		String[] browsedFilePaths = new String[] {};
		// fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {

				if (f.isDirectory()) {
					return true;
				}

				String extension = getExtension(f);
				if (extension != null) {
					if (extension.equals("mp3")) {
						return true;
					}
				}
				return false;
			}

			@Override
			public String getDescription() {
				return ".mp3";
			}

			/**
			 * Get the lower case extension of a file.
			 */
			private String getExtension(File f) {
				String ext = null;
				String s = f.getName();
				int i = s.lastIndexOf('.');

				if (i > 0 && i < s.length() - 1) {
					ext = s.substring(i + 1).toLowerCase();
				}
				return ext;
			}

		});
		// fileChooser.getSelectedFiles();
		// show open file dialog
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) // user chose a file
		{
			List<String> pats = new ArrayList<String>();
			if (fileChooser.getSelectedFile() != null) {
				for (int i = 0; i < fileChooser.getSelectedFiles().length; i++) {
					pats.add(fileChooser.getSelectedFiles()[i]
							.getAbsolutePath());
				}
				browsedFilePaths = pats.toArray(new String[pats.size()]);
				// play();
			}
			fileChooser.setCurrentDirectory(fileChooser.getSelectedFiles()[0]
					.getParentFile());
		}

		return browsedFilePaths;
	}

	private void resetAttributes() {
		StringBuffer sb = new StringBuffer();
		sb.append("Resetting attributes : \n")
				.append("\nCurrent playlist : \n");
		if (filePaths != null) {
			for (int i = 0; i < filePaths.length; i++) {
				sb.append("\t" + (i + 1) + "\t\t: " + filePaths[i] + "\n");
			}
			sb.append("\tCurrently playing track no : " + (getCounter() + 1)
					+ "\n\t" + filePaths[getCounter()]);
		}
		System.out.println(sb.toString());
		setCurrentPlayFinished(false);
		// autoIncrement = true;
		setCurrentInterruption(Interruption.CONTINUE);
	}

	private CustomPlaybackListener customPlaybackListener;

	public void play(URL urlAsString) {
		try {
			if (urlAsString != null) {
				resetAttributes();
				System.out.println(" Playing : " + urlAsString);
				this.setPlayer(new AdvancedPlayer(urlAsString.openStream(),
						javazoom.jl.player.FactoryRegistry.systemRegistry()
								.createAudioDevice()));
				// this.getPlayer().setPlayBackListener(this);
				this.getPlayer().setPlayBackListener(customPlaybackListener);
				this.playerThread = new Thread(this, "AudioPlayerThread");
				playerThread.setDaemon(true);
				this.playerThread.start();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if (this.playerThread != null && this.playerThread != null
				&& !(currentPlayFinished)) {
			this.playerThread.stop();
			// autoIncrement = false;
			setCurrentInterruption(Interruption.STOP);
			this.getPlayer().stop();
		}
	}

	public void playTrackNo(int i) {
		if (this.playerThread != null && this.playerThread != null) {
			this.playerThread.stop();
			// autoIncrement = false;
			setCounter(i);
			setCurrentInterruption(Interruption.JUMP);
			if (!currentPlayFinished) {
				this.getPlayer().stop();
			}
		}
	}

	public void next() {
		if (this.playerThread != null && this.playerThread != null) {
			this.playerThread.stop();

			if (!currentPlayFinished) {
				setCurrentInterruption(Interruption.NEXT);
				this.getPlayer().stop();
			}
		}
	}

	public void previous() {
		if (this.playerThread != null && this.playerThread != null) {
			this.playerThread.stop();

			if (!currentPlayFinished) {
				setCurrentInterruption(Interruption.PREVIOUS);
				this.getPlayer().stop();
			}
		}
	}

	public void newPlaylist() {
		if (this.playerThread != null && this.playerThread != null) {
			// /////////////
			System.out
					.println("select new playlist or cancel browse title window to resume current song");
			String[] fs = getBrowsedFiles();
			if (fs != null && fs.length > 0) {
				setFilePaths(fs);
				this.playerThread.stop();
				if (!currentPlayFinished) {
					setCurrentInterruption(Interruption.BROWSE_NEW);
					this.getPlayer().stop();
				}
			}
			// ////////////
			// this.playerThread.stop();
			// currentInterruption = Interruption.BROWSE_NEW;
			// this.player.stop();
		} else {
			String[] fs = getBrowsedFiles();
			if (fs != null && fs.length > 0) {
				setFilePaths(fs);
				play();
			}
		}
	}

	public void addMoreSongs() {
		if (this.playerThread != null && this.playerThread != null) {
			// //////////////////
			System.out
					.println("select new playlist or cancel browse title window to resume current song."
							+ " if selected then new songs will be added and current song will be resumed.");
			String[] fs = getBrowsedFiles();
			if (fs != null && fs.length > 0) {
				System.out.println("merging new selected songs to playlist");

				String[] oldArr = getFilePaths();
				int oldLength = (oldArr != null) ? oldArr.length : 0;
				String[] mergeArr = new String[oldLength + fs.length];
				int i = 0;
				for (int j = 0; j < oldLength; j++) {
					mergeArr[i++] = oldArr[j];
				}
				for (int j = 0; j < fs.length; j++) {
					mergeArr[i++] = fs[j];
				}
				setFilePaths(mergeArr);
				// this.playerThread.stop();
				// currentInterruption = Interruption.BROWSE_ADD;
				// this.player.stop();
				stop();
				play();
			}
			// //////////////////
			// this.playerThread.stop();
			// currentInterruption = Interruption.BROWSE_ADD;
			// this.player.stop();
		}
	}

	public void play() {
		try {
			// String urlAsString = "file:///"
			// + new java.io.File(".").getCanonicalPath() + "/"
			// + this.filePath;

			if (filePaths != null && filePaths.length > 0) {
				String urlAsString = "file:///" + this.filePaths[getCounter()];

				resetAttributes();
				System.out.println(" Playing : " + urlAsString);
				this.setPlayer(new AdvancedPlayer(new java.net.URL(urlAsString)
						.openStream(), javazoom.jl.player.FactoryRegistry
						.systemRegistry().createAudioDevice()));
				// this.getPlayer().setPlayBackListener(this);
				this.getPlayer().setPlayBackListener(customPlaybackListener);
				this.playerThread = new Thread(this, "AudioPlayerThread");
				playerThread.setDaemon(true);
				this.playerThread.start();

				// ////////////////

				// while (!currentPlayFinished) {
				// // Create a Scanner object for taking input from cmd
				// System.out
				// .println("Enter following to control : "
				// + "\n\t1. Stop : s / stop"
				// + "\n\t2. Next : n / next"
				// + "\n\t3 Previous : p / previous"
				// + "\n\t4 Browse New Playlist : bnew / bnew  "
				// + "\n\t5 Browse to add to current playlist : badd / badd  ");
				// Scanner s = new Scanner(System.in);
				// //
				//
				// // Read a line and store it in st
				// String st = s.nextLine();
				// // If user types 's', stop the audio
				// if (st.equalsIgnoreCase("s") || st.equalsIgnoreCase("stop"))
				// {
				// stop();
				// } else if (st.equalsIgnoreCase("n")
				// || st.equalsIgnoreCase("next")) {
				// next();
				// } else if (st.equalsIgnoreCase("p")
				// || st.equalsIgnoreCase("previous")) {
				// previous();
				// } else if (st.equalsIgnoreCase("bnew")
				// || st.equalsIgnoreCase("bnew")) {
				// newPlaylist();
				// } else if (st.equalsIgnoreCase("badd")
				// || st.equalsIgnoreCase("badd")) {
				// addMoreSongs();
				// } else {
				// System.out.println("Invalid command!");
				// }
				// }
			} else {
				System.out.println("nothin to play... exiting.");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// PlaybackListener members

	public void playbackStarted(PlaybackEvent playbackEvent) {
		// System.out.println("playbackStarted()");
	}

	public void playbackFinished(PlaybackEvent playbackEvent) {
		// System.out.println("playbackEnded()");
		setCurrentPlayFinished(true);
		// if (autoIncrement) {
		// counter = ((counter + 1) % (filePaths.length));
		// // play();
		// }
		System.out.println(getCurrentInterruption().name());
		if (filePaths != null && filePaths.length > 0) {
			if (getCurrentInterruption() != Interruption.STOP) {
				if (getCurrentInterruption() == Interruption.CONTINUE) {
					setCounter(((getCounter() + 1) % (filePaths.length)));
					play();
				} else if (getCurrentInterruption() == Interruption.NEXT) {
					setCounter(((getCounter() + 1) % (filePaths.length)));
					play();
				} else if (getCurrentInterruption() == Interruption.PREVIOUS) {
					setCounter(((getCounter() - 1 + filePaths.length) % (filePaths.length)));
					// counter = ((counter - 1) + (filePaths.length));
					// counter = (counter > 0 ? counter : (0 - counter));
					play();
				} else if (getCurrentInterruption() == Interruption.BROWSE_NEW) {
					// System.out
					// .println("select new playlist or cancel browse title window to resume current song");
					// ///////////////////
					// String[] fs = getBrowsedFiles();
					// if (fs != null && fs.length > 0) {
					// setFilePaths(fs);
					// counter = 0;
					// }
					// ////////////////////
					setCounter(0);
					play();
				} else if (getCurrentInterruption() == Interruption.BROWSE_ADD) {
					/**
					 * Do nothing user has right to add new songs in his
					 * playlist indeed
					 */
					// System.out
					// .println("select new playlist or cancel browse title window to resume current song."
					// +
					// " if selected then new songs will be added and current song will be resumed.");

					// String[] fs = getBrowsedFiles();
					// if (fs != null && fs.length > 0) {
					// System.out
					// .println("merging new selected songs to playlist");
					// String[] mergeArr = new String[getFilePaths().length
					// + fs.length];
					// int i = 0;
					// for (int j = 0; j < getFilePaths().length; j++) {
					// mergeArr[i++] = getFilePaths()[j];
					// }
					// for (int j = 0; j < fs.length; j++) {
					// mergeArr[i++] = fs[j];
					// }
					// setFilePaths(mergeArr);
					// }
					play();
				}
			} else {
				System.out.println("Forced to stop!!");
			}
		}

	}

	// Runnable members

	public void run() {
		try {
			this.getPlayer().play();
		} catch (javazoom.jl.decoder.JavaLayerException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @return the filePaths
	 */
	public String[] getFilePaths() {
		return filePaths;
	}

	/**
	 * @param filePaths
	 *            the filePaths to set
	 */
	public void setFilePaths(String[] filePaths) {
		this.filePaths = filePaths;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter
	 *            the counter to set
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * @return the currentPlayFinished
	 */
	public boolean isCurrentPlayFinished() {
		return currentPlayFinished;
	}

	/**
	 * @param currentPlayFinished
	 *            the currentPlayFinished to set
	 */
	public void setCurrentPlayFinished(boolean currentPlayFinished) {
		this.currentPlayFinished = currentPlayFinished;
	}

	/**
	 * @return the player
	 */
	public AdvancedPlayer getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(AdvancedPlayer player) {
		this.player = player;
	}

	/**
	 * @return the customPlaybackListener
	 */
	public CustomPlaybackListener getCustomPlaybackListener() {
		return customPlaybackListener;
	}

	/**
	 * @param customPlaybackListener
	 *            the customPlaybackListener to set
	 */
	public void setCustomPlaybackListener(
			CustomPlaybackListener customPlaybackListener) {
		this.customPlaybackListener = customPlaybackListener;
	}
}